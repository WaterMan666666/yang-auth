package com.fireman.yang.auth.core.common.enums;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public enum GrantType {

    /** 密码式: 用户名和密码 更换Token */
    password,
    /**隐藏式 直接颁发令牌*/
    token;

    public static GrantType toEnum(String code) {
        for (GrantType item : GrantType.values()) {
            if (item.name().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
