package com.fireman.yang.auth.core.client;

import com.fireman.yang.auth.core.login.LoginToken;
import com.fireman.yang.auth.session.Session;
import com.fireman.yang.auth.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public interface AuthClientManager {

    /**
     * 用户登录
     */
    SessionToken login(LoginToken token);
    /**
     * 用户是否已经登录
     */
    Session checkLogin(SessionToken token);
    /**
     * 用户登出
     */
    void logout(SessionToken token);



}
