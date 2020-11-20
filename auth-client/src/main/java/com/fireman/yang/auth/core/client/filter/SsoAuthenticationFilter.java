package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.handler.UnAuthenticateHandler;
import com.fireman.yang.auth.core.login.LoginTokenFactory;
import com.fireman.yang.auth.core.session.SessionTokenFactory;

import javax.servlet.FilterChain;
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

    public SsoAuthenticationFilter(AuthClientConfig config, SessionTokenFactory sessionTokenFactory, LoginTokenFactory loginTokenFactory, AuthClientManager clientManager, UnAuthenticateHandler handler) {
        super(config, clientManager, sessionTokenFactory, loginTokenFactory);
        this.handler = handler;
    }


    @Override
    protected void unAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handler.handler(request, response);
        continueChain(request, false);
    }

    @Override
    protected void dealLoginGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) {
        //执行登录的逻辑
        dealLoginPost(httpServletRequest, httpServletResponse, filterChain);
        //登录页的请求,执行原始的Filter,放行
    }
}
