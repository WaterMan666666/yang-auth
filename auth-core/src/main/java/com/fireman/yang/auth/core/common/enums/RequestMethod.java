package com.fireman.yang.auth.core.common.enums;

/**
 * @author tongdong
 * @Date: 2020/11/10
 * @Description:
 */
public enum  RequestMethod {

    GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    public static RequestMethod toEnum(String code) {
        for (RequestMethod item : RequestMethod.values()) {
            if (item.name().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
