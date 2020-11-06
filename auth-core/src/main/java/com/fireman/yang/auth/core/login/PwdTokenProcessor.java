package com.fireman.yang.auth.core.login;


import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.Token;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public class PwdTokenProcessor extends LoginTokenProcessor<PasswordToken> {


    @Override
    public User authenticate(PasswordToken token) {
        return null;
    }

    @Override
    public boolean isTypeMatch(Token token) {
        return token instanceof PasswordToken;
    }
}
