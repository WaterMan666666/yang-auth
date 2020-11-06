package com.fireman.yang.auth.core.client.filter;

import com.fireman.yang.auth.core.client.eunms.AuthFilterEnum;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description: 什么都通过的Filter
 */
public class AnonymousFilter extends AbstractPathFilter {

    public AnonymousFilter() {
        super(AuthFilterEnum.anon.name());
    }

    @Override
    protected void preHanlder(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        continueOriginChain(filterChain);
    }
}
