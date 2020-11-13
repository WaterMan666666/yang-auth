package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.common.enums.ResponseType;
import com.fireman.yang.auth.core.login.LoginToken;
import com.fireman.yang.auth.core.login.LoginTokenFactory;
import com.fireman.yang.auth.core.server.AuthSsoServerManager;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;
import com.fireman.yang.auth.core.server.service.AuthServiceService;
import com.fireman.yang.auth.core.server.support.ResponseTypeProcessor;
import com.fireman.yang.auth.session.SessionToken;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public class LoginController {


    private static Logger log = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private AuthSsoServerManager serverManager;
    @Autowired
    private LoginTokenFactory loginTokenFactory;
    @Autowired
    private AuthenticationController authenticationController;
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGet(){
        log.info("登录调用");
        return "login";
    }

    /**
     * 登录提交
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void loginPost(@RequestParam(value = "response_type", required = false) String responseType,
                          @RequestParam(value = "client_id", required = false) String clientId,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "login_url", required = false) String loginUrl,
                          @RequestParam(name = "bizType", required = false) String bizType,
                          HttpServletRequest requset){
        //校验信息的完整
        checkInfo();
        //获取用户登录凭证
        LoginToken loginToken = loginTokenFactory.generateLoginToken(requset);
        //登录校验
        serverManager.login(loginToken);
        //授权处理
        authenticationController.authorize(responseType, clientId, redirectUri, loginUrl ,bizType);
    }

    private void checkInfo() {

    }
}