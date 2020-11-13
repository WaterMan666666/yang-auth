package com.fireman.yang.auth.core.exception;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public class AuthenticateException extends AuthException {

    public AuthenticateException(int code, String msg) {
        super(code, msg);
    }
}
