package com.fireman.yang.auth.core.client;

import com.fireman.yang.auth.core.login.LoginToken;
import com.fireman.yang.auth.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class AuthUtils {


    private static AuthClientManager authClientManager;

    public static SessionToken login(LoginToken token){

        return authClientManager.login(token);
    }

    public static void logout(){
//        RequestContextHolder.getRequestAttributes()).getRequest
//        authClientManager.logout();

    }
}
