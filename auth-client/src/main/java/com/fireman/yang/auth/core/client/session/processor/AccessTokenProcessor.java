package com.fireman.yang.auth.core.client.session.processor;

import com.fireman.yang.auth.core.client.ClientSessionDao;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.client.session.AccessToken;
import com.fireman.yang.auth.core.client.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.common.Token;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class AccessTokenProcessor extends SessionTokenProcessor<AccessToken> {

    public AccessTokenProcessor(ClientSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private ClientSessionDao sessionDao;

    @Override
    public Session parseToken(AccessToken token) {
        return sessionDao.readSession(token.getType(), token.getToken());
    }

    @Override
    public boolean isTypeMatch(Token token) {
        return token instanceof AccessToken;
    }

}
