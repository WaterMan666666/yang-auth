package com.fireman.yang.auth.core.service;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.login.PasswordToken;

import java.lang.reflect.InvocationTargetException;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public interface AuthService {

    /**
     * 验证toke并获取用户信息
     */
    User authenticate(PasswordToken passwordToken);
}
