package com.fireman.yang.auth.core.web;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public interface FilterChainResolver {

    /**
     * 将Servlet容器传递过来的过滤器 和 Auth 自定义的过滤器进行组装根据请求的路径
     */
    FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originChain);
}
