package com.fireman.yang.auth.core.client.filter.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public class AuthFilterChain implements FilterChain {

    private static final Logger log = LoggerFactory.getLogger(AuthFilterChain.class);

    private List<Filter> filters;
    private FilterChain originChain;
    private int index = 0;

    public AuthFilterChain(List<Filter> filters, FilterChain originChain) {
        this.originChain = originChain;
        this.filters = filters;
        this.index = 0;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (this.filters == null || this.filters.size() == this.index) {
            originChain.doFilter(request,response);
        } else {
            this.filters.get(this.index++).doFilter(request, response, this);
        }
    }

    public void doOriginChain(){
        this.index = this.filters.size();
    }
}
