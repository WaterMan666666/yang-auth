package com.fireman.yang.auth.core.login;

import com.fireman.yang.auth.core.common.enums.SessionType;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public class AuthorizeCodeToken extends LoginToken {


    public AuthorizeCodeToken(String code, SessionType sessionType) {
        super(sessionType);
        this.code = code;
    }

    private String code;

    @Override
    public String getToken() {
        return code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
