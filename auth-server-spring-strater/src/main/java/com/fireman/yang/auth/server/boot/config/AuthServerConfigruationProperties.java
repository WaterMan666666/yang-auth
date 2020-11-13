package com.fireman.yang.auth.server.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * @author tongdong
 * @Date: 2020/11/2
 * @Description:
 */

@ConfigurationProperties(prefix = "yang.auth.client")
public class AuthServerConfigruationProperties {

    private static final Logger log = LoggerFactory.getLogger(AuthServerConfigruationProperties.class);

    /** 是否开启 */
    private boolean enable;

    /** 登录URL */
    private String loginUrl;

    /** 登录模式 */
    private String scop;

    /** session超时时间 */
    private Integer sessionExpire;

    /** 用户文件信息 */
    private String userInfoPath;

    @PostConstruct
    public void check(){

    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getScop() {
        return scop;
    }

    public void setScop(String scop) {
        this.scop = scop;
    }

    public Integer getSessionExpire() {
        return sessionExpire;
    }

    public void setSessionExpire(Integer sessionExpire) {
        this.sessionExpire = sessionExpire;
    }

    public String getUserInfoPath() {
        return userInfoPath;
    }

    public void setUserInfoPath(String userInfoPath) {
        this.userInfoPath = userInfoPath;
    }
}
