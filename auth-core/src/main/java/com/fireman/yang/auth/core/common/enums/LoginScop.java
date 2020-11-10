package com.fireman.yang.auth.core.common.enums;

/**
 * @author tongdong
 * @Date: 2020/6/12
 * @Description:
 */
public enum LoginScop {

    /**同一个用户登录仅能存在一个session实例*/
    singleton,
    /**同一个用户登录能存在多个session实例*/
    prototype;

    public static LoginScop toEnum(String code) {
        for (LoginScop item : LoginScop.values()) {
            if (item.name().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
