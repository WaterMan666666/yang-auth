package com.fireman.yang.auth.core.common;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface Processor<T> {


    /**
     * 是否匹配类型
     */
    boolean isTypeMatch(T t);
}
