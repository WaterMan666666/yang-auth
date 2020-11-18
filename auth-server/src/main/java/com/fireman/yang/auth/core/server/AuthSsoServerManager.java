package com.fireman.yang.auth.core.server;

import com.fireman.yang.auth.core.login.LoginToken;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public interface AuthSsoServerManager extends AuthServerManager{

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
