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
        this.sessionTokenProcessors = sessionTokenProcessors;
        this.loginTokenProcessors = loginTokenProcessors;
        this.sessionFactory = sessionFactory;
        this.sessionTokenFactory = sessionTokenFactory;
        this.sessionDao = sessionDao;
        this.authorizeUri = authorizeUri;
        this.tokenUri = tokenUri;
        this.loginUri = loginUri;
        this.infoBasePath = infoBasePath;
    }

    private LoginScop scop;
    private List<SessionTokenProcessor> sessionTokenProcessors;
    private List<LoginTokenProcessor> loginTokenProcessors;
    private SessionFactory sessionFactory;
    private SessionTokenFactory sessionTokenFactory;
    private ServerSessionDao sessionDao;
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

    public List<SessionTokenProcessor> getSessionTokenProcessors() {
        return sessionTokenProcessors;
    }

    public void setSessionTokenProcessors(List<SessionTokenProcessor> sessionTokenProcessors) {
        this.sessionTokenProcessors = sessionTokenProcessors;
    }

    public List<LoginTokenProcessor> getLoginTokenProcessors() {
        return loginTokenProcessors;
    }

    public void setLoginTokenProcessors(List<LoginTokenProcessor> loginTokenProcessors) {
        this.loginTokenProcessors = loginTokenProcessors;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionTokenFactory getSessionTokenFactory() {
        return sessionTokenFactory;
    }

    public void setSessionTokenFactory(SessionTokenFactory sessionTokenFactory) {
        this.sessionTokenFactory = sessionTokenFactory;
    }

    public ServerSessionDao getSessionDao() {
        return sessionDao;
    }

    public void setSessionDao(ServerSessionDao sessionDao) {
        this.sessionDao = sessionDao;
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
