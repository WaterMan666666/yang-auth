package com.fireman.yang.auth.core.web;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
public interface PatternMatcher {

    boolean matches(String pattern, String source);
}
