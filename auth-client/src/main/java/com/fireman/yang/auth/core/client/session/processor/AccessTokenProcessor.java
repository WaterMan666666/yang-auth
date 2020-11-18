package com.fireman.yang.auth.core.client.session.processor;

import com.fireman.yang.auth.core.client.ClientSessionDao;
import com.fireman.yang.auth.core.sso.AccessToken;
import com.fireman.yang.auth.core.client.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class AccessTokenProcessor extends SessionTokenProcessor {

    public AccessTokenProcessor(ClientSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private ClientSessionDao sessionDao;

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

