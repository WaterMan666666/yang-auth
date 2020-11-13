package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.login.LoginToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
@Controller
public class TokenController {

    /**
     * 登录提交
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public void loginPost(@RequestParam(value = "grant_type", required = false) String grantType,
                          @RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "client_id", required = false) String clientId,
                          HttpServletRequest requset){

        //获取用户登录凭证

    }
}
