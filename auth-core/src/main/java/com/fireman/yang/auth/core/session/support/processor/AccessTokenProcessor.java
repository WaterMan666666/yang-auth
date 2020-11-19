package com.fireman.yang.auth.core.session.support.processor;

import com.fireman.yang.auth.core.session.*;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class AccessTokenProcessor extends SessionTokenProcessor {

    public AccessTokenProcessor(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private SessionDao sessionDao;

    @Override
    public boolean isTypeMatch(SessionToken sessionToken) {
        return sessionToken instanceof AccessToken;
    }

    @Override
    public Session parseToken(SessionToken sessionToken) {
        AccessToken token = (AccessToken) sessionToken;
        return sessionDao.readSession(token.getType(), token.getToken());
    }
}

