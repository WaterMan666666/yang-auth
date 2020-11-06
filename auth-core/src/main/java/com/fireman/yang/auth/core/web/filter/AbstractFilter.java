package com.fireman.yang.auth.core.web.filter;

import com.fireman.yang.auth.core.common.Nameable;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/11/4
 * @Description:
 */
public abstract class AbstractFilter implements Filter, Nameable {

    public AbstractFilter(String name) {
        this.name = name;
    }

    private String name;

    protected FilterConfig filterConfig;

    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.setFilterConfig(filterConfig);
        this.servletContext = filterConfig.getServletContext();
        if (this.name == null) {
            FilterConfig config = this.getFilterConfig();
            if (config != null) {
                this.name = config.getFilterName();
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        this.doFilterInternal((HttpServletRequest)request, (HttpServletResponse)response, filterChain);
    }

    @Override
    public void destroy() {

    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    protected abstract void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException;
}
