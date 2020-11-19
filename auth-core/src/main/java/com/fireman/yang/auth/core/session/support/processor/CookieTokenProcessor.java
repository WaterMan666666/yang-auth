package com.fireman.yang.auth.core.session.support.processor;

import com.fireman.yang.auth.core.session.*;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class CookieTokenProcessor extends SessionTokenProcessor {

    public CookieTokenProcessor(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private SessionDao sessionDao;

    @Override
    public Session parseToken(SessionToken token) {
        return sessionDao.readSession(token.getType(), token.getToken());
    }

    @Override
    public boolean isTypeMatch(SessionToken token) {
        return token instanceof CookieToken;
    }

}
