package com.fireman.yang.auth.core.client.config;

import com.fireman.yang.auth.core.client.session.ClientSessionDao;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.login.LoginTokenFactory;
import com.fireman.yang.auth.core.login.LoginTokenProcessor;
import com.fireman.yang.auth.core.session.SessionFactory;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.core.session.SessionTokenProcessor;

import java.util.List;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public class AuthClientConfig {

    public AuthClientConfig(List<String> filters, Map<String, List<String>> mapping,
                            LoginScop scop, Integer sessionExpire, String clientId, String clientSecret,
                            String loginUri, String authDomain, String authTokenUri) {
        this.filters = filters;
        this.mapping = mapping;
        this.scop = scop;
        this.sessionExpire = sessionExpire;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.loginUri = loginUri;
        this.authDomain = authDomain;
        this.authTokenUri = authTokenUri;
    }

    private List<String> filters;

    private Map<String, List<String>> mapping;

    private LoginScop scop;

    private Integer sessionExpire;

    private String clientId;

    private String clientSecret;

    private String loginUri;

    String authDomain;

    String authTokenUri;

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

    public String getLoginUri() {
        return loginUri;
    }

    public void setLoginUri(String loginUri) {
        this.loginUri = loginUri;
    }

    public String getAuthDomain() {
        return authDomain;
    }

    public void setAuthDomain(String authDomain) {
        this.authDomain = authDomain;
    }

    public String getAuthTokenUri() {
        return authTokenUri;
    }

    public void setAuthTokenUri(String authTokenUri) {
        this.authTokenUri = authTokenUri;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
