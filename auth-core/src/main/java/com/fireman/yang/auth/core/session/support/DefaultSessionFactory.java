package com.fireman.yang.auth.core.session.support;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionFactory;

import java.util.UUID;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class DefaultSessionFactory implements SessionFactory {
    @Override
    public Session generateSession(User user) {
        DefaultSession defaultSession = new DefaultSession();
        defaultSession.setUser(user);
        defaultSession.setId(UUID.randomUUID().toString().replaceAll("-",""));
        defaultSession.setAttribute("123","123");
        return defaultSession;
    }
}
