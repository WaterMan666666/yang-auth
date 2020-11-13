package com.fireman.yang.auth.core.login;


import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.Token;
import com.fireman.yang.auth.core.service.AuthService;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class PwdTokenProcessor extends LoginTokenProcessor<PasswordToken> {

    private AuthService authService;

    public PwdTokenProcessor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public User authenticate(PasswordToken token) {
        return authService.authenticate(token);
    }

    @Override
    public boolean isTypeMatch(PasswordToken token) {
        return token instanceof PasswordToken;
    }
}
