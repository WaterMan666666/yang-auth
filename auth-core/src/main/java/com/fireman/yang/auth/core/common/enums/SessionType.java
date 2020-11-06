package com.fireman.yang.auth.core.common.enums;

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
    Cookie
}
