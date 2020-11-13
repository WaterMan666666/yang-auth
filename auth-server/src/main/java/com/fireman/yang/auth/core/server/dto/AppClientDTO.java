package com.fireman.yang.auth.core.server.dto;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public class AppClientDTO {

    /** 系统ID */
    private String clientId;
    /** 系统名称 */
    private String clientName;
    /** 状态 */
    private String status;
    /** 系统密钥 */
    private String secretKey;
    /** 系统域名 */
    private String domain;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
