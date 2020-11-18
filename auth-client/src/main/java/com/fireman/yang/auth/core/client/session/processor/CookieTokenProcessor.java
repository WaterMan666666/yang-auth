package com.fireman.yang.auth.core.client.session.processor;

import com.fireman.yang.auth.core.client.ClientSessionDao;
import com.fireman.yang.auth.core.client.session.CookieToken;
import com.fireman.yang.auth.core.client.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class CookieTokenProcessor extends SessionTokenProcessor{

    public CookieTokenProcessor(ClientSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private ClientSessionDao sessionDao;

    @Override
    public Session parseToken(SessionToken token) {
        return sessionDao.readSession(token.getType(), token.getToken());
    }

    @Override
    public boolean isTypeMatch(SessionToken token) {
        return token instanceof CookieToken;
    }

}
