package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.common.constants.AuthConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class DefaultSession implements Session {

    Map<String ,Object> map = new ConcurrentHashMap<>();


    @Override
    public void setId(String id) {
        map.put(AuthConstants.AUTH_ID, id);
    }

    @Override
    public String getId() {
        Object o = map.get(AuthConstants.AUTH_ID);
        return o == null ? null : o.toString();
    }

    @Override
    public Object getAttribute(String key) {
        return map.get(key);
    }

    @Override
    public void setAttribute(String key, Object value) {
        map.put(AuthConstants.AUTH_ID, value);
    }

    @Override
    public Object removeAttribute(String key) {
        return map.remove(key);
    }

    @Override
    public void setUser(User user) {
        map.put(AuthConstants.AUTH_USER, user);
    }

    @Override
    public User getUser() {
        Object o = map.get(AuthConstants.AUTH_ID);
        if(o == null){
            return null;
        }
        return o instanceof User ? (User)o : null;
    }
}
