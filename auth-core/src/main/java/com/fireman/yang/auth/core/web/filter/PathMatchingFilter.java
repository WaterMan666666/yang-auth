package com.fireman.yang.auth.core.web.filter;

import com.fireman.yang.auth.core.web.PatternMatcher;
import com.fireman.yang.auth.core.web.support.PathMatcher;
import com.fireman.yang.auth.core.web.utils.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author tongdong
 * @Date: 2020/11/4
 * @Description:
 */
public class PathMatchingFilter extends AbstractFilter {

    private final String CONTINUE_HANDLER = "CONTINUE_HANDLER";
    private final String CONTINUE_CHAIN = "CONTINUE_CHAIN";

    protected PatternMatcher pathMatcher = new PathMatcher();

    public PathMatchingFilter(String name) {
        super(name);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        init(servletRequest);
        preHanlder(servletRequest, servletResponse, filterChain);
        boolean continueHandler = continueHandler(servletRequest);
        if(continueHandler) {
            this.doHandler(servletRequest, servletResponse, filterChain);
        }
        boolean continueChain = continueChain(servletRequest);
        if(continueChain) {
            executeChain(servletRequest,servletResponse,filterChain);
        }
        afterHandler(servletRequest, servletResponse);
    }

    private void init(ServletRequest servletRequest) {
        continueHandler(servletRequest, Boolean.TRUE);
        continueChain(servletRequest, Boolean.TRUE);
    }



    /**
     * 当前Filter执行前的逻辑处理
     */
    protected void preHanlder(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

    }
    /**
     * 后置处理
     */
    protected void afterHandler(ServletRequest servletRequest, ServletResponse servletResponse) {

    }
    /**
     * 是否继续执行后续Filter的逻辑
     */
    protected boolean continueChain(ServletRequest servletRequest){
        return Boolean.valueOf(servletRequest.getAttribute(CONTINUE_CHAIN).toString());
    }

    /**
     * 是否处理当前Filter内的业务逻辑
     */
    protected boolean continueHandler(ServletRequest servletRequest) {
        return Boolean.valueOf(servletRequest.getAttribute(CONTINUE_HANDLER).toString());
    }

    /**
     * 继续处理当前Filter内的业务逻辑
     */
    protected void continueHandler(ServletRequest servletRequest,  boolean isContinue) {
        servletRequest.setAttribute(CONTINUE_HANDLER, isContinue);
    }

    /**
     * 继续执行后续Filter的逻辑
     */
    protected void continueChain(ServletRequest servletRequest,  boolean isContinue) {
        servletRequest.setAttribute(CONTINUE_CHAIN, isContinue);
    }

    /**
     * 执行业务逻辑
     */
    protected void doHandler(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {

    }

    /**
     * 执行后续Filter的逻辑
     */
    protected void executeChain(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }

    protected boolean matchPath(HttpServletRequest httpServletRequest, String... path){
        String requestURI = WebUtils.getPathWithinApplication(httpServletRequest);
        boolean match = Stream.of(path).anyMatch(item -> pathMatcher.matches(requestURI, item));
        return match;
    }

}
