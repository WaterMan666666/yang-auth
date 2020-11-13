package com.fireman.yang.auth.core.client.config;

import com.fireman.yang.auth.core.client.ClientSessionDao;
import com.fireman.yang.auth.session.SessionFactory;
import com.fireman.yang.auth.session.SessionTokenFactory;
import com.fireman.yang.auth.core.client.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.login.LoginTokenFactory;
import com.fireman.yang.auth.core.login.LoginTokenProcessor;

import java.util.List;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public class AuthClientConfig {

    public AuthClientConfig(List<String> filters, Map<String, List<String>> mapping, ClientSessionDao sessionDao,
                            LoginScop scop, Integer sessionExpire, String clientId, List<SessionTokenProcessor> sessionTokenProcessors,
                            List<LoginTokenProcessor> loginTokenProcessors, SessionFactory sessionFactory,
                            SessionTokenFactory sessionTokenFactory,LoginTokenFactory loginTokenFactory, String loginUrl) {
        this.filters = filters;
        this.mapping = mapping;
        this.sessionDao = sessionDao;
        this.scop = scop;
        this.sessionExpire = sessionExpire;
        this.clientId = clientId;
        this.sessionTokenProcessors = sessionTokenProcessors;
        this.loginTokenProcessors = loginTokenProcessors;
        this.sessionFactory = sessionFactory;
        this.sessionTokenFactory = sessionTokenFactory;
        this.loginTokenFactory = loginTokenFactory;
        this.loginUrl = loginUrl;
    }

    private List<String> filters;

    private Map<String, List<String>> mapping;

    private ClientSessionDao sessionDao;

    private LoginScop scop;

    private Integer sessionExpire;

    private String clientId;

    private List<SessionTokenProcessor> sessionTokenProcessors;

    private List<LoginTokenProcessor> loginTokenProcessors;

    private SessionFactory sessionFactory;

    private SessionTokenFactory sessionTokenFactory;

    private LoginTokenFactory loginTokenFactory;

    private String loginUrl;

    public ClientSessionDao getSessionDao() {
        return sessionDao;
    }

    public void setSessionDao(ClientSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public LoginScop getScop() {
        return scop;
    }

    public void setScop(LoginScop scop) {
        this.scop = scop;
    }

    public Integer getSessionExpire() {
        return sessionExpire;
    }

    public void setSessionExpire(Integer sessionExpire) {
        this.sessionExpire = sessionExpire;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public Map<String, List<String>> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, List<String>> mapping) {
        this.mapping = mapping;
    }

    public LoginTokenFactory getLoginTokenFactory() {
        return loginTokenFactory;
    }

    public void setLoginTokenFactory(LoginTokenFactory loginTokenFactory) {
        this.loginTokenFactory = loginTokenFactory;
    }
}
