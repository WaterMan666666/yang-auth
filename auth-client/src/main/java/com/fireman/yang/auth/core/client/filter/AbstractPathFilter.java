package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.filter.manager.AuthFilterChain;
import com.fireman.yang.auth.core.web.filter.PathMatchingFilter;

import javax.servlet.FilterChain;

/**
 * @author tongdong
 * @Date: 2020/11/5
 * @Description:
 */
public class AbstractPathFilter extends PathMatchingFilter {
    public AbstractPathFilter(String name) {
        super(name);
    }

    /**
     * 执行外部剩下的filter
     */
    protected void continueOriginChain(FilterChain filterChain){
        if(filterChain instanceof AuthFilterChain){
            AuthFilterChain chain = (AuthFilterChain) filterChain;
            chain.doOriginChain();
        }
    }
}
