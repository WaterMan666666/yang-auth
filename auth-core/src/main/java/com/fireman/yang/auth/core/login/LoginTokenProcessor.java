package com.fireman.yang.auth.core.login;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.TokenProcessor;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public abstract class LoginTokenProcessor<T extends LoginToken> implements TokenProcessor<T> {


    /**
     * 验证toke并获取用户信息
     */
    public abstract User authenticate(T token);
}
