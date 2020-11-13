package com.fireman.yang.auth.core.exception;

import com.fireman.yang.auth.core.common.enums.ReturnCode;

/**
 * @ClassName ParameterErrorException
 * @Author DYB
 * @Date 2019/1/16 14:00
 * @Description
 * @Version V1.0
 */
public class ParameterErrorException extends AuthException {
    public ParameterErrorException(String msg) {

        super(ReturnCode.BAD_REQUEST.getCode(), msg);
    }
}
