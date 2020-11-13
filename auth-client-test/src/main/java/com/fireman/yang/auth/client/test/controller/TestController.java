package com.fireman.yang.auth.client.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tongdong
 * @Date: 2020/7/6
 * @Description:
 */
@RestController
public class TestController {


    private static Logger logger = LoggerFactory.getLogger(TestController.class);


    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        logger.info("登录调用");
        return "login";
    }

    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/sd", method = RequestMethod.GET)
    public String sd(){
        logger.info("登录调用");
        return "login";
    }
}
