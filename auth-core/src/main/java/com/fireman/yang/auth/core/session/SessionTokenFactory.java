package com.fireman.yang.auth.core.session;


import com.fireman.yang.auth.core.common.enums.SessionType;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 创建的sessionToken的工厂
 */
public interface SessionTokenFactory {

    /**
     * 创建会话的Token
     */
    SessionToken generateSessionToken();

    /**
     * 创建会话的Token
     */
    SessionToken generateSessionToken(SessionType sessionType, Session session);
}
