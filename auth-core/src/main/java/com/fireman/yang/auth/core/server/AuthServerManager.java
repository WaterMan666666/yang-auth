package com.fireman.yang.auth.core.server;

import com.fireman.yang.auth.core.User;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public interface AuthServerManager {

    /**
     * 获取所有登录人及登录系统的信息
     */
    List<User> getAllOlineUsers();
    /**
     * 将某位用户强制登出
     */
    void logout(User user);
}
