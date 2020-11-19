package com.fireman.yang.auth.core.session;


import com.fireman.yang.auth.core.common.Processor;

/**
 * @author tongdong
 * @Date: 2020/6/10
 * @Description:
 */
public abstract class SessionTokenProcessor implements Processor<SessionToken> {


    /**
     * 验证toke并获取Session信息
     */
    public abstract Session parseToken(SessionToken token);
}
