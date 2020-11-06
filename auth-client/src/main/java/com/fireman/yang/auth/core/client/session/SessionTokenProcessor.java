package com.fireman.yang.auth.core.client.session;


import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.common.TokenProcessor;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public abstract class SessionTokenProcessor<T extends SessionToken> implements TokenProcessor<T> {


    /**
     * 验证toke并获取Session信息
     */
    public abstract Session parseToken(T token);
}
