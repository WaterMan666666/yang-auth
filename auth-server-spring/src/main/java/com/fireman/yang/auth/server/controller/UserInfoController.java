package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.server.AuthSsoServerManager;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
@RestController
public class UserInfoController {

    @Autowired
    private AuthSsoServerManager serverManager;
    @Autowired
    private SessionTokenFactory sessionTokenFactory;


    @RequestMapping(value = "/oauth/info/user",method = RequestMethod.GET)
    public User userinfo(){
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken();
        Session session = serverManager.checkLogin(sessionToken);
        if(session == null){
            return new User();
        }
        return session.getUser();
    }

}
