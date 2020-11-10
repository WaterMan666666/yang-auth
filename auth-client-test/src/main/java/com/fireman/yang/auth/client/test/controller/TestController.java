package com.fireman.yang.auth.client.test.controller;

import com.fireman.yang.auth.core.client.AuthUtils;
import com.fireman.yang.auth.core.client.session.SessionToken;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.login.PasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tongdong
 * @Date: 2020/7/6
 * @Description:
 */
@Controller
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

}
