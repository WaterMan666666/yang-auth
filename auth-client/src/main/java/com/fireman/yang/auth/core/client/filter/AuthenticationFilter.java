package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.eunms.AuthFilterEnum;
import com.fireman.yang.auth.core.common.ThreadContext;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.RequestMethod;
import com.fireman.yang.auth.core.login.LoginToken;
import com.fireman.yang.auth.core.login.LoginTokenFactory;

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
        this.loginUrl = config.getLoginUri();
        this.clientManager = clientManager;
        this.sessionTokenFactory = config.getSessionTokenFactory();
        this.sessionTokenFactory = config.getSessionTokenFactory();
        this.loginTokenFactory = config.getLoginTokenFactory();
    }

    private String loginUrl;

    private AuthClientManager clientManager;

    private SessionTokenFactory sessionTokenFactory;

    private LoginTokenFactory loginTokenFactory;

    /**
     * 执行业务逻辑
     */
    @Override
    protected void doHandler(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        saveHttpInfo(httpServletRequest, httpServletResponse);
        if(checkLoginPath(httpServletRequest)){
            //处理登录的情况
            dealLogin(httpServletRequest, httpServletResponse, filterChain);
            return;
        }
        if(isAuthenticate()){
            //已登录, 放行
            return;
        }
        //未登录 重定向到Login页面
        unAuthenticate(httpServletRequest, httpServletResponse);
    }

    private void saveHttpInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ThreadContext.put(AuthConstants.AUTH_HTTP_REQUEST, httpServletRequest);
        ThreadContext.put(AuthConstants.AUTH_HTTP_RESPONSE, httpServletResponse);
    }

    private boolean checkLoginPath(HttpServletRequest httpServletRequest) {
        return matchPath(httpServletRequest, loginUrl);
    }

    private boolean isAuthenticate(){
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken();
        Session session = clientManager.checkLogin(sessionToken);
        if(session != null) {
            ThreadContext.put(AuthConstants.AUTH_SESSION_TOKEN, sessionToken);
            return true;
        }
        return false;
    }

    protected void unAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //未登录则，重定向到Login页面
        //返回未登录信息
        response.sendRedirect(loginUrl);
        //handler
        continueChain(request, false);
    }


    protected void dealLogin(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException {
        String method = httpServletRequest.getMethod();
         RequestMethod requestMethod = RequestMethod.toEnum(method);
        if(requestMethod != null) {
            switch (requestMethod){
                case GET:
                    //登录页的请求,执行原始的Filter,放行
                    continueOriginChain(filterChain);
                    break;
                case POST:
                    LoginToken loginToken = loginTokenFactory.generateLoginToken();
                    SessionToken sessionToken = clientManager.login(loginToken);
                    sessionToken.afterLogin(httpServletRequest, httpServletResponse);
                    continueChain(httpServletRequest, false);
                    break;
                default:
                    continueChain(httpServletRequest, false);
            }
        }
    }

}
