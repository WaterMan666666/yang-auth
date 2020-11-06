package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.client.SessionTokenFactory;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.eunms.AuthFilterEnum;
import com.fireman.yang.auth.core.client.session.SessionToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description: 这个filter处理的是登录状态的校验
 */
public class AuthenticationFilter extends AbstractPathFilter {

    public AuthenticationFilter(AuthClientConfig config, AuthClientManager clientManager) {
        super(AuthFilterEnum.auth.name());
        this.loginUrl = config.getLoginUrl();
        this.clientManager = clientManager;
        this.sessionTokenFactory = config.getSessionTokenFactory();
    }

    private String loginUrl;

    private AuthClientManager clientManager;

    private SessionTokenFactory sessionTokenFactory;

    /**
     * 执行业务逻辑
     */
    @Override
    protected void doHandler(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if(checkLoginPath(httpServletRequest)){
            //登录页的请求,执行原始的Filter,放行
            continueOriginChain(filterChain);
            return;
        }

        if(isAuthenticate(httpServletRequest)){
            //已登录, 放行
            return;
        }
        //未登录 重定向到Login页面
        unAuthenticate(servletRequest,servletResponse);
    }

    private boolean checkLoginPath(HttpServletRequest httpServletRequest) {
        return matchPath(httpServletRequest, loginUrl);
    }

    private boolean isAuthenticate(HttpServletRequest httpServletRequest){
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken(httpServletRequest);
        Session session = clientManager.checkLogin(sessionToken);
        return session != null;
    }

    protected void unAuthenticate(ServletRequest request, ServletResponse response) throws IOException {
        //未登录则，重定向到Login页面
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //返回未登录信息
        httpResponse.sendRedirect(loginUrl);
        //handler
        continueChain(request, false);
    }
}
