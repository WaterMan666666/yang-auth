package com.fireman.yang.auth.core.common.enums;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public enum ResponseType {

    /** 授权码模式 */
    code,
    /**隐藏式 直接颁发令牌*/
    token;

    public static ResponseType toEnum(String code) {
        for (ResponseType item : ResponseType.values()) {
            if (item.name().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
