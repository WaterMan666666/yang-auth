package com.fireman.yang.auth.core.server.dto;

/**
 * @author tongdong
 * @Date: 2020/11/17
 * @Description:
 */
public class GrantTokenDTO {


    public GrantTokenDTO(String code, String redirectUri, String clientId) {
        this.code = code;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
    }

    private String code;
    private String redirectUri;
    private String clientId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
