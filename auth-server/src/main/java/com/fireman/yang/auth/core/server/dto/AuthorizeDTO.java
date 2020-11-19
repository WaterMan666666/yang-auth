package com.fireman.yang.auth.core.server.dto;


import javax.validation.constraints.NotBlank;

/**
 * @ClassName AuthorizeDTO
 * @Author TD
 */

public class AuthorizeDTO {


    public AuthorizeDTO(String responseType, String clientId, String redirectUri) {
        this.responseType = responseType;
        this.clientId = clientId;
        this.redirectUri = redirectUri;
    }

    /** 授权类型 */
    @NotBlank(message = "授权类型不能为空")
    private String responseType;
    /** 客户端的ID */
    @NotBlank(message = "客户端的ID不能为空")
    private String clientId;
    /** 重定向URI */
    @NotBlank(message = "重定向URI不能为空")
    private String redirectUri;
    /** 权限范围 */
    private String scope;
    /** 需要跳转到的前端域名，改URL必须和redirectUri 同域 */
    private String originUrl;
    /** 自定义登录 */
    private String loginUri;

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getLoginUri() {
        return loginUri;
    }

    public void setLoginUri(String loginUri) {
        this.loginUri = loginUri;
    }
}
