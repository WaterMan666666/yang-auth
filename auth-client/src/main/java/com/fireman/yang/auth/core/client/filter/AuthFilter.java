package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.filter.manager.AuthFilterChainManager;
import com.fireman.yang.auth.core.web.FilterChainResolver;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/4
 * @Description:
 */
public class AuthFilter implements Filter {

    private FilterChainResolver filterChainResolver;


    public AuthFilter(AuthClientConfig clientConfig, Map<String, Filter> filterMap) {
        this.filterChainResolver = new AuthFilterChainManager(clientConfig, filterMap);
    }

    public void setFilterChainResolver(FilterChainResolver filterChainResolver) {
        this.filterChainResolver = filterChainResolver;
    }

    /**
     * 更新session的时间
     */
    private void updateSessionLastAccessTime(ServletRequest request, ServletResponse response) {

    }

    private FilterChain getExecutionChain(ServletRequest request, ServletResponse response, FilterChain originChain) {
        return filterChainResolver.getChain(request, response, originChain);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain originChain) throws IOException, ServletException {
        //当创建出session时，则需要管理session信息
        updateSessionLastAccessTime(request, response);
        //创建组装执行链
        FilterChain chain = getExecutionChain(request, response, originChain);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
