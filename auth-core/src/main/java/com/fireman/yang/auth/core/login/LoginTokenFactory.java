package com.fireman.yang.auth.core.login;


/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 创建的sessionToken的工厂
 */
public interface LoginTokenFactory {

    /**
     * 创建登录的Token
     */
    LoginToken generateLoginToken();

}
