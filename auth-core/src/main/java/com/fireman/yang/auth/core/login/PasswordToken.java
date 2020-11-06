package com.fireman.yang.auth.core.login;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class PasswordToken extends LoginToken {

    private String username;
    private String password;

    public PasswordToken(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getToken() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
