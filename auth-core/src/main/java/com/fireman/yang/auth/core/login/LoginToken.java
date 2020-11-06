package com.fireman.yang.auth.core.login;


import com.fireman.yang.auth.core.common.Token;
import com.fireman.yang.auth.core.common.enums.SessionType;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public abstract class LoginToken implements Token {


    private SessionType sessionType;

    public LoginToken() {
    }


    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }
}
