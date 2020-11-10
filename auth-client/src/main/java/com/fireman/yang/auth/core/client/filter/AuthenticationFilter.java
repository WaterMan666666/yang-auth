package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.client.SessionTokenFactory;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.eunms.AuthFilterEnum;
import com.fireman.yang.auth.core.client.session.SessionToken;
import com.fireman.yang.auth.core.common.ThreadContext;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.RequestMethod;
import com.fireman.yang.auth.core.login.LoginToken;
import com.fireman.yang.auth.core.login.LoginTokenFactory;
import com.fireman.yang.auth.core.web.utils.StringUtils;
import com.fireman.yang.auth.core.web.utils.WebUtils;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        if(checkLoginPath(httpServletRequest)){
            //处理登录的情况
            dealLogin(httpServletRequest, httpServletResponse, filterChain);
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
        if(session != null) {
            ThreadContext.put(AuthConstants.AUTH_SESSION_TOKEN, sessionToken);
            return true;
        }
        return false;
    }

    protected void unAuthenticate(ServletRequest request, ServletResponse response) throws IOException {
        //未登录则，重定向到Login页面
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //返回未登录信息
        httpResponse.sendRedirect(loginUrl);
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
                    LoginToken loginToken = loginTokenFactory.generateLoginToken(httpServletRequest);
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
