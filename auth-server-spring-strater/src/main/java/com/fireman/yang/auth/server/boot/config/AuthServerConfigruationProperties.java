package com.fireman.yang.auth.server.boot.config;

import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.web.utils.StringUtils;
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
    private String loginUri;

    /** 登录模式 */
    private String scop;

    /** session超时时间 */
    private Integer sessionExpire;

    /** code超时时间 */
    private Integer codeExpire;

    /** 用户文件信息 */
    private String infoBasePath;

    private String authorizeUri;

    private String tokenUri;

    @PostConstruct
    public void check(){
        LoginScop scop = LoginScop.toEnum(this.scop);
        if(scop == null){
            scop = LoginScop.singleton;
        }
        if(StringUtils.isBlank(authorizeUri)){
            authorizeUri = "/oauth/sso/authorize";
        }
        if(StringUtils.isBlank(tokenUri)){
            tokenUri = "/oauth/token";
        }
        if(StringUtils.isBlank(loginUri)){
            loginUri = "/oauth/login";
        }
        if(sessionExpire == null || sessionExpire == 0){
            sessionExpire = AuthConstants.SESSION_EXPIRE_DEFAULT;
        }
        if(codeExpire == null || codeExpire == 0){
            codeExpire = AuthConstants.CODE_EXPIRE_DEFAULT;
        }
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
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

    public String getInfoBasePath() {
        return infoBasePath;
    }

    public void setInfoBasePath(String infoBasePath) {
        this.infoBasePath = infoBasePath;
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

    public Integer getCodeExpire() {
        return codeExpire;
    }

    public void setCodeExpire(Integer codeExpire) {
        this.codeExpire = codeExpire;
    }
}
