package com.fireman.yang.auth.session;


import com.fireman.yang.auth.core.common.Processor;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public abstract class SessionTokenProcessor<T extends SessionToken> implements Processor<T> {


    /**
     * 验证toke并获取Session信息
     */
    public abstract Session parseToken(T token);
}
