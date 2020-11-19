package com.fireman.yang.auth.core.server.service;

import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;
import com.fireman.yang.auth.core.server.dto.GrantTokenDTO;
import com.fireman.yang.auth.core.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/11/13
 * @Description:
 */
public interface AuthServerCoreService {

    /**
     * 授权检查
     */
    String authorize(AuthorizeDTO authorizeDTO,  boolean isAuthenticate);
    /**
     * 发放token
     */
    SessionToken grantToken(GrantTokenDTO grantTokenDTO);
}
