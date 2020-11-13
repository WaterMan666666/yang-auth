package com.fireman.yang.auth.core.exception;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public class AuthException extends RuntimeException {

    /** 返回错误码 */
    private int code;
    /** 返回错误信息 */
    private String msg;


    public AuthException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
