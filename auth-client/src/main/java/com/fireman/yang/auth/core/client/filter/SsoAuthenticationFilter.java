package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.handler.UnAuthenticateHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public class SsoAuthenticationFilter extends AuthenticationFilter {

    private UnAuthenticateHandler handler;

    public SsoAuthenticationFilter(AuthClientConfig config, AuthClientManager clientManager, UnAuthenticateHandler handler) {
        super(config, clientManager);
        this.handler = handler;
    }


    @Override
    protected void unAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handler.handler(request, response);
    }
}
