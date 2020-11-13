package com.fireman.yang.auth.core.server.service;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.login.PasswordToken;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public interface AuthServiceService {

    /**
     * 验证AppClient是否存在
     */
    AppClientDTO getAppClient(String clientId);
    /**
     * 验证toke并获取用户信息
     */
    User authenticate(PasswordToken passwordToken);
}
