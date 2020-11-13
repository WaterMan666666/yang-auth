package com.fireman.yang.auth.core.login;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.Processor;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public abstract class LoginTokenProcessor<T extends LoginToken> implements Processor<T> {


    /**
     * 验证toke并获取用户信息
     */
    public abstract User authenticate(T token);
}
