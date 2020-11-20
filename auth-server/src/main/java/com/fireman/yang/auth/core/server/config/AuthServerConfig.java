package com.fireman.yang.auth.core.server.config;

import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.login.LoginTokenProcessor;
import com.fireman.yang.auth.core.server.support.ServerSessionDao;
import com.fireman.yang.auth.core.session.SessionFactory;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.core.session.SessionTokenProcessor;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public class AuthServerConfig {


    public AuthServerConfig(LoginScop scop, List<SessionTokenProcessor> sessionTokenProcessors, List<LoginTokenProcessor> loginTokenProcessors, SessionFactory sessionFactory, SessionTokenFactory sessionTokenFactory, ServerSessionDao sessionDao, String infoBasePath,String authorizeUri, String tokenUri, String loginUri) {
        this.scop = scop;
        this.authorizeUri = authorizeUri;
        this.tokenUri = tokenUri;
        this.loginUri = loginUri;
        this.infoBasePath = infoBasePath;
    }

    private LoginScop scop;
    private String authorizeUri;
    private String infoBasePath;
    private String tokenUri;
    private String loginUri;

    public LoginScop getScop() {
        return scop;
    }

    public void setScop(LoginScop scop) {
        this.scop = scop;
    }

    public String getAuthorizeUri() {
        return authorizeUri;
    }

    public void setAuthorizeUri(String authorizeUri) {
        this.authorizeUri = authorizeUri;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public void setTokenUri(String tokenUri) {
        this.tokenUri = tokenUri;
    }

    public String getLoginUri() {
        return loginUri;
    }

    public void setLoginUri(String loginUri) {
        this.loginUri = loginUri;
    }

    public String getInfoBasePath() {
        return infoBasePath;
    }

    public void setInfoBasePath(String infoBasePath) {
        this.infoBasePath = infoBasePath;
    }
}
