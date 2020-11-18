package com.fireman.yang.auth.core.common.enums;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public enum  ReturnCode {

    OK(200, "成功"),
    BAD_REQUEST(400, "请求无法被服务器理解"),
    UNAUTHORIZED(401, "未授权"),
    UNLOGIN(472, "未登陆"),
    SYSTEM_ERROR(500, "系统错误"),
    ;

    private int code;
    private String msg;

    ReturnCode(int code, String msg) {
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
