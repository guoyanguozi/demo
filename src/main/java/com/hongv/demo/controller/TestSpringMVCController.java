package com.hongv.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
