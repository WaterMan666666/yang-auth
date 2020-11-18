package com.fireman.yang.auth.core.exception;

import com.fireman.yang.auth.core.common.enums.ReturnCode;

/**
 * @ClassName ParameterErrorException
 * @Author DYB
 * @Date 2019/1/16 14:00
 * @Description
 * @Version V1.0
 */
public class SystemErrorException extends AuthException {
    public SystemErrorException(String msg, Throwable e) {

        super(ReturnCode.SYSTEM_ERROR.getCode(), msg, e);
    }

    public SystemErrorException(String msg) {

        super(ReturnCode.SYSTEM_ERROR.getCode(), msg);
    }
}
