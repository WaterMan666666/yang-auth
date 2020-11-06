package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public class SsoAuthenticationFilter extends AuthenticationFilter {


    public SsoAuthenticationFilter(AuthClientConfig config, AuthClientManager clientManager) {
        super(config, clientManager);
    }
}
