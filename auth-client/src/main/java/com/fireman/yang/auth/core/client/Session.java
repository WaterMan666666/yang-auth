package com.fireman.yang.auth.core.client;

import com.fireman.yang.auth.core.User;

/**
 * @author tongdong
 * @Date: 2020/6/8
 * @Description:
 */
public interface Session {


    void setId(String id);

    String getId();

    Object getAttribute(String key);

    void setAttribute(String key, Object value);

    Object removeAttribute(String key);

    void setUser(User user);

    User getUser();

}
