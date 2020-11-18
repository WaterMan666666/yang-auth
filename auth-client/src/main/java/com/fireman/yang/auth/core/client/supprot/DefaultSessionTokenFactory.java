package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.common.ThreadContext;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.core.sso.AccessToken;
import com.fireman.yang.auth.core.client.session.CookieToken;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.web.utils.CookieUtils;
import com.fireman.yang.auth.core.web.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class DefaultSessionTokenFactory implements SessionTokenFactory {

    public DefaultSessionTokenFactory(String clientId) {
        this.clientId = clientId;
    }

    private String clientId;

    @Override
    public SessionToken generateSessionToken() {
        HttpServletRequest request = ThreadContext.getRequest();
        //获取AccessToken
        String accessToke = request.getHeader(AuthConstants.AUTHORIZATION);
        if(!StringUtils.isBlank(accessToke) && accessToke.length() > AuthConstants.BEARER.length()){
            return new AccessToken(accessToke.substring(AuthConstants.BEARER.length()));
        }
        //获取Cookie
        String sid = CookieUtils.getValue(request, CookieToken.getCookieKey(clientId));
        if(!StringUtils.isBlank(sid)){
            return new CookieToken(clientId, sid);
        }
        return null;
    }

    @Override
    public SessionToken generateSessionToken(SessionType sessionType, Session session) {
        switch (sessionType){
            case AccessToken:
                return new AccessToken(session.getId());
            case Cookie:
                return new CookieToken(clientId, session.getId());
            case Code:
        }
        return null;
    }
}
