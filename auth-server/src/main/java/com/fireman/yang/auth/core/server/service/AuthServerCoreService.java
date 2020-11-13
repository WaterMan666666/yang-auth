package com.fireman.yang.auth.core.server.service;

import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;

/**
 * @author tongdong
 * @Date: 2020/11/13
 * @Description:
 */
public interface AuthServerCoreService {

    void authorize(AuthorizeDTO authorizeDTO);
}
