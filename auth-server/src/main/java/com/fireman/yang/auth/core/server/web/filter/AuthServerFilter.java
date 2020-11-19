package com.fireman.yang.auth.core.server.web.filter;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.common.ThreadContext;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.ReturnCode;
import com.fireman.yang.auth.core.server.AuthServerManager;
import com.fireman.yang.auth.core.server.AuthSsoServerManager;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.core.web.filter.PathMatchingFilter;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public class AuthServerFilter extends PathMatchingFilter {


    private AuthSsoServerManager ssoServerManager;

    private SessionTokenFactory sessionTokenFactory;

    private String[] annoUri;




    public AuthServerFilter(AuthSsoServerManager ssoServerManager, SessionTokenFactory sessionTokenFactory, String... annoUri) {
        super("auth-server");
        this.ssoServerManager = ssoServerManager;
        this.sessionTokenFactory = sessionTokenFactory;
        this.annoUri = annoUri;
    }

    @Override
    protected void doHandler(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        saveHttpInfo(httpServletRequest, httpServletResponse);
        if(checkAnonPath(httpServletRequest)){
            return;
        }
        if(isAuthenticate()){
            //已登录, 放行
            return;
        }
        //未登录 重定向到Login页面
        unAuthenticate(httpServletRequest, httpServletResponse);
    }

    private boolean isAuthenticate(){
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken();
        Session session = ssoServerManager.checkLogin(sessionToken);
        if(session != null) {
            ThreadContext.put(AuthConstants.AUTH_SESSION_TOKEN, sessionToken);
            return true;
        }
        return false;
    }

    protected void unAuthenticate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //未登录则，重定向到Login页面
        //返回未登录信息
         response.setStatus(ReturnCode.UNAUTHORIZED.getCode());
        PrintWriter writer = response.getWriter();
        Map<String,String> map = new HashMap<>(2);
        map.put("msg", String.valueOf(ReturnCode.UNAUTHORIZED.getMsg()));
        writer.write(JsonUtils.toJsonString(map));
        //handler
        continueChain(request, false);
    }


    private void saveHttpInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ThreadContext.put(AuthConstants.AUTH_HTTP_REQUEST, httpServletRequest);
        ThreadContext.put(AuthConstants.AUTH_HTTP_RESPONSE, httpServletResponse);
    }

    private boolean checkAnonPath(HttpServletRequest httpServletRequest) {
        if(annoUri != null && annoUri.length > 0){
            for (int i = 0; i < annoUri.length; i++) {
                if(matchPath(httpServletRequest, annoUri[i])){
                    return true;
                }
            }
        }
        return false;
    }
}
