package com.fireman.yang.auth.core.sso;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.login.AuthorizeCodeToken;
import com.fireman.yang.auth.core.session.AccessToken;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public interface ClientSsoService {

    /**
     * 验证toke并获取用户信息
     */
    AccessToken authenticate(AuthorizeCodeToken token);

    /**
     * 获取用户信息
     */
    User getUserInfo(AccessToken token);
}
