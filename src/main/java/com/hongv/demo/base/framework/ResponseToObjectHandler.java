package com.hongv.demo.base.framework;

import com.hongv.demo.base.framework.codec.JSONCodec;
import com.hongv.demo.base.framework.web.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * 修改spring mvc 请求方法返回结果的类型
 * Created by hongwei on 2017/4/18.
 */
public class ResponseToObjectHandler implements HandlerMethodReturnValueHandler {
    private static final Set<Class<?>> SUPPORT_RETURN_VALUE_TYPE_SET = new HashSet<>();

    static {
        SUPPORT_RETURN_VALUE_TYPE_SET.add(String.class);
        SUPPORT_RETURN_VALUE_TYPE_SET.add(ResponseWrapper.class);
        SUPPORT_RETURN_VALUE_TYPE_SET.add(Integer.class);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Class<?> parameterType = returnType.getParameterType();
        if (SUPPORT_RETURN_VALUE_TYPE_SET.contains(parameterType)) {
            logger.info("返回值类型是：{}", parameterType);
            return true;
        }
        logger.info("返回值类型是：{}, 不支持转化", parameterType);
        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (returnValue == null) {
            // 这个被（@RequestMapping）注解的方法的 url=xxxx （即 @RequestMapping（value="xxxx"））
            String path = webRequest.getContextPath();
            logger.warn("return value is null, " + path);
            mavContainer.setRequestHandled(true);
            return;
        }
        //        获取response对象
        HttpServletResponse response = (HttpServletResponse) webRequest.getNativeResponse();
        // 设置response的类型
        response.setContentType("application/json; charset=utf-8");
        // 将returnValue（即@RequestMapping注解注解的方法返回值）输出到response的body中
        try {
            PrintWriter writer = response.getWriter();
            // 输出到body中
            // FIXME 这里如果是 ResponseWrapper类型， 直接print（returnValue）会返回一个对象指针，应该返回该对象转成json的字符串
            if(returnValue instanceof ResponseWrapper){
                // 如果是ResponseWrapper对象, 直接转成Json
                returnValue = JSONCodec.encode(returnValue);
            }
            writer.print(returnValue);
            // 刷到网络io流中
            writer.flush();
        } catch (IOException e) {
            logger.warn("http相应输出失败", e);
        }
        // 告诉springmvc 这个请求（ webRequest.getContextPath()）完全处理完毕了。
        mavContainer.setRequestHandled(true);
    }
}
