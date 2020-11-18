package com.fireman.yang.auth.client.boot.config;

import com.fireman.yang.auth.core.client.eunms.AuthModel;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.exception.ParameterErrorException;
import com.fireman.yang.auth.core.web.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/2
 * @Description:
 */

@ConfigurationProperties(prefix = "yang.auth.client")
public class AuthClientConfigruationProperties {

    private static final Logger log = LoggerFactory.getLogger(AuthClientConfigruationProperties.class);

    /** 是否开启 */
    private boolean enable;

    /** 模式 sso/singleton */
    private String model;

    /** 系统标识 */
    private String clientId;

    /** 系统交互密钥 */
    private String clientSecret;

    /** 登录URL */
    private String loginUri;

    /** 登录模式 */
    private String scop;

    /** session超时时间 */
    private Integer sessionExpire;

    /** 过滤器映射关系 */
    private Map<String, List<String>> mapping;

    /** 过滤器链条 */
    private List<String> filters;

    /** 文件信息的基类 */
    private String infoBasePath;

    String authDomain;

    String authTokenUri;


    @PostConstruct
    public void check(){
        AuthModel model = AuthModel.toEnum(this.model);
        if(model == null){
            model = AuthModel.single;
        }
        if(StringUtils.isBlank(clientId)){
            clientId = "Default";
        }
        if(StringUtils.isBlank(loginUri)){
            loginUri = "/login";
        }
        LoginScop scop = LoginScop.toEnum(this.scop);
        if(scop == null){
            scop = LoginScop.singleton;
        }
        if(sessionExpire == null){
            sessionExpire = AuthConstants.SESSION_EXPIRE_DEFAULT;
        }
        switch (model){
            case sso:
                if(StringUtils.isBlank(authDomain)){
                    log.error("sso model propertie < authDomain > not exist");
                    throw new ParameterErrorException("authDomain not exist ");
                }
                if(StringUtils.isBlank(authTokenUri)){
                    authTokenUri = "/oauth/token";
                }
                break;
            case single:
                if(StringUtils.isBlank(clientId)){
                    log.error("sso model propertie < clientId > not exist");
                    throw new ParameterErrorException("clientId not exist ");
                }
                if(StringUtils.isBlank(clientSecret)){
                    log.error("sso model propertie < clientSecret > not exist");
                    throw new ParameterErrorException("clientSecret not exist ");
                }
                break;
            default:
        }
        //检查filter的逻辑是否正确
        if(filters == null){
            filters = new ArrayList<>(0);
        }
        final List<String> list = filters;
        if(mapping != null){
            boolean allMatch = mapping.entrySet().stream().map(item -> item.getValue())
                    .flatMap(item -> item.stream()).distinct()
                    .allMatch(item -> list.contains(item));
            if(!allMatch){
                log.error("mapping filter item not exist in filter list");
                throw new ParameterErrorException("auth mapping filter not exist ");
            }
        }else{
            mapping = new HashMap<>(0);
        }
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Map<String, List<String>> getMapping() {
        return mapping;
    }

    public void setMapping(Map<String, List<String>> mapping) {
        this.mapping = mapping;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
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

    public String getInfoBasePath() {
        return infoBasePath;
    }

    public void setInfoBasePath(String infoBasePath) {
        this.infoBasePath = infoBasePath;
    }


}
