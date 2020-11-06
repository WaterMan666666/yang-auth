package com.fireman.yang.auth.boot.config;

import com.fireman.yang.auth.core.client.eunms.AuthModel;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.web.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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
public class AuthConfigruationProperties {

    private static final Logger log = LoggerFactory.getLogger(AuthConfigruationProperties.class);

    /** 是否开启 */
    private boolean enable;

    /** 模式 sso/singleton */
    private String model;

    /** 系统标识 */
    private String clientId;

    /** 登录URL */
    private String loginUrl;

    /** 登录模式 */
    private String scop;

    /** session超时时间 */
    private Integer sessionExpire;

    /** 过滤器映射关系 */
    private Map<String, List<String>> mapping;

    /** 过滤器链条 */
    private List<String> filters;


    @PostConstruct
    public void check(){
        AuthModel model = AuthModel.toEnum(this.model);
        if(model == null){
            model = AuthModel.single;
        }
        if(StringUtils.isBlank(clientId)){
            clientId = "Default";
        }
        if(StringUtils.isBlank(loginUrl)){
            loginUrl = "/login";
        }
        LoginScop scop = LoginScop.toEnum(this.scop);
        if(scop == null){
            scop = LoginScop.Singleton;
        }
        if(sessionExpire == null){
            sessionExpire = AuthConstants.SESSION_EXPIRE_DEFAULT;
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
                throw new RuntimeException("auth mapping filter not exist ");
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

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
