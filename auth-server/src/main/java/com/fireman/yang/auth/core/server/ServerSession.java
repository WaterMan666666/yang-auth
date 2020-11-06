package com.fireman.yang.auth.core.server;

import com.fireman.yang.auth.core.User;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/2
 * @Description:
 */
public class ServerSession {

    /**
     * 用户Id
     */
    private String userId;


    /**
     * 用户信息
     */
    private User user;


    /**
     * 登录实例
     */
    private List<AuthInstance> instances;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<AuthInstance> getInstances() {
        return instances;
    }

    public void setInstances(List<AuthInstance> instances) {
        this.instances = instances;
    }
}
