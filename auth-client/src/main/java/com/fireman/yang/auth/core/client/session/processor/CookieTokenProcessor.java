package com.fireman.yang.auth.core.client.session.processor;

import com.fireman.yang.auth.core.client.ClientSessionDao;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.client.session.CookieToken;
import com.fireman.yang.auth.core.client.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.common.Token;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class CookieTokenProcessor extends SessionTokenProcessor<CookieToken> {

    public CookieTokenProcessor(ClientSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private ClientSessionDao sessionDao;

    @Override
    public Session parseToken(CookieToken token) {
        return sessionDao.readSession(token.getType(), token.getToken());
    }

    @Override
    public boolean isTypeMatch(Token token) {
        return token instanceof CookieToken;
    }

}
