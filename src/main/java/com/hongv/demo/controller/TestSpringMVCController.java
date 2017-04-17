package com.hongv.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hongv.demo.base.framework.web.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 测试spring
 * Created by hongwei on 2017/4/17.
 */
@Controller
@RequestMapping(value = "/test")
public class TestSpringMVCController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /***
     *
     *  建议参考此url
     *  http://docs.spring.io/spring/docs/4.3.x/spring-framework-reference/html/mvc.html#mvc-ann-return-types
     */

    /**
     * @GetMapping == @RequestMapping(method = RequestMethod.GET)
     */
    @GetMapping(value = "/hello/v1")
    public String helloV1UseJsonView() {
        return "abc";
    }

    /**
     * ResponseWrapper 的使用
     * 参考 {@ResponseToObjectHandler的实现}
     */
    @GetMapping(value = "/responseWrapper")
    public ResponseWrapper responseWrapper() {
        return new ResponseWrapper().addObject("testKeyA", "testValueA");
    }

    @RequestMapping
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "Joe") String name,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        logger.info("收到了一个请求， name={}, request={}, response={}", name, request, response);
        if (!"Joe".equals(name)) {
            return "hello " + name;
        }

        return "hello world";
    }

    @RequestMapping(value = "/jsp", method = RequestMethod.GET)
    public ModelAndView getJspPage() {
        return new ModelAndView("index");
    }
}
