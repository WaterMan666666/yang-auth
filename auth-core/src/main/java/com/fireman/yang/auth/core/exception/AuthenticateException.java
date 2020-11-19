package com.fireman.yang.auth.core.exception;

import com.fireman.yang.auth.core.common.enums.ReturnCode;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public class AuthenticateException extends AuthException {

    public AuthenticateException(String msg) {
        super(ReturnCode.UNAUTHORIZED.getCode(), msg);
    }
}
