package com.fireman.yang.auth.core.client.eunms;

/**
 * @author tongdong
 * @Date: 2020/11/5
 * @Description:
 */
public enum AuthModel {

    /** 单机 */
    single,
    /** 单点登录 */
    sso;


    public static AuthModel toEnum(String code) {
        for (AuthModel item : AuthModel.values()) {
            if (item.name().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
