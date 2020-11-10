package com.fireman.yang.auth.core.common.enums;

import com.fireman.yang.auth.core.web.utils.StringUtils;

/**
 * @author tongdong
 * @Date: 2020/6/12
 * @Description:
 */
public enum SessionType {
    /**返回授权码*/
    Code,
    /**返回token*/
    AccessToken,
    /**同一个用户登录能存在多个session实例*/
    Cookie;

    public static SessionType toEnum(String code) {
        if(StringUtils.isBlank(code)){
            return null;
        }
        for (SessionType item : SessionType.values()) {
            if (item.name().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
