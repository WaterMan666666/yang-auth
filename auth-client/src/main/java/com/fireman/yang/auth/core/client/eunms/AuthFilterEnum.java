package com.fireman.yang.auth.core.client.eunms;

import com.fireman.yang.auth.core.client.filter.AnonymousFilter;
import com.fireman.yang.auth.core.client.filter.AuthenticationFilter;
import com.fireman.yang.auth.core.web.filter.PathMatchingFilter;

/**
 * @author tongdong
 * @Date: 2020/7/2
 * @Description: 过滤器的顺序
 */
public enum AuthFilterEnum {

    /**匿名通过*/
    anon(AnonymousFilter.class),
    /**客户端端认证*/
    auth(AuthenticationFilter.class);



    public Class<PathMatchingFilter> filterClass;

    AuthFilterEnum(Class clazz){
        this.filterClass = clazz;
    }


    public static AuthFilterEnum toEnum(String code) {
        for (AuthFilterEnum item : AuthFilterEnum.values()) {
            if (item.name().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
