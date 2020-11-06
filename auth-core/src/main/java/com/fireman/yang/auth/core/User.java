package com.fireman.yang.auth.core;

/**
 * @author tongdong
 * @Date: 2020/6/12
 * @Description:
 */
public class User {

    private String id;

    private String username;

    private String realName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
