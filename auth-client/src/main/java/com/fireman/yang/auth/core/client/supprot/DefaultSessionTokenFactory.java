package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.client.SessionTokenFactory;
import com.fireman.yang.auth.core.client.session.SessionToken;
import com.fireman.yang.auth.core.common.enums.SessionType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class DefaultSessionTokenFactory implements SessionTokenFactory {
    @Override
    public SessionToken generateSessionToken(HttpServletRequest request) {
        return null;
    }

    @Override
    public SessionToken generateSessionToken(SessionType sessionType, Session session) {
        return null;
    }
}
