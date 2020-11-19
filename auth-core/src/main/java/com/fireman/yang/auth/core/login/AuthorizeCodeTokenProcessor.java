package com.fireman.yang.auth.core.login;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.ThreadContext;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.session.AccessToken;
import com.fireman.yang.auth.core.sso.ClientSsoService;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public class AuthorizeCodeTokenProcessor extends LoginTokenProcessor {

    public AuthorizeCodeTokenProcessor(ClientSsoService clientSsoService) {
        this.clientSsoService = clientSsoService;
    }

    private ClientSsoService clientSsoService;

    @Override
    public User authenticate(LoginToken loginToken) {
        AuthorizeCodeToken token = (AuthorizeCodeToken)loginToken;
        AccessToken accessToken = clientSsoService.authenticate(token);
        ThreadContext.put(AuthConstants.ACCESS_TOKEN, accessToken);
        return clientSsoService.getUserInfo(accessToken);
    }

    @Override
    public boolean isTypeMatch(Object token) {
        return token instanceof AuthorizeCodeToken;
    }
}
