package com.fireman.yang.auth.core.client;


import com.fireman.yang.auth.core.client.session.SessionToken;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.login.LoginToken;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 创建的sessionToken的工厂
 */
public interface SessionTokenFactory {

    /**
     * 创建会话的Token
     */
    SessionToken generateSessionToken(HttpServletRequest request);

    /**
     * 创建会话的Token
     */
    SessionToken generateSessionToken(SessionType sessionType, Session session);
}
