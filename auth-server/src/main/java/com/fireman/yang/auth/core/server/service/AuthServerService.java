package com.fireman.yang.auth.core.server.service;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.login.PasswordToken;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.service.AuthService;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public interface AuthServerService extends AuthService {

    /**
     * 验证AppClient是否存在
     */
    AppClientDTO getAppClient(String clientId);
}
