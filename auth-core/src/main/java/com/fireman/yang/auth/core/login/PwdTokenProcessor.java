package com.fireman.yang.auth.core.login;


import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.Token;
import com.fireman.yang.auth.core.service.AuthService;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class PwdTokenProcessor extends LoginTokenProcessor<LoginToken> {

    private AuthService authService;

    public PwdTokenProcessor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public User authenticate(LoginToken loginToken) {
        PasswordToken token = (PasswordToken)loginToken;
        return authService.authenticate(token);
    }

    @Override
    public boolean isTypeMatch(LoginToken token) {
        return token instanceof PasswordToken;
    }
}
