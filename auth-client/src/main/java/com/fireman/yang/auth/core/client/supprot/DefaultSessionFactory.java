package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.client.SessionFactory;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class DefaultSessionFactory implements SessionFactory {
    @Override
    public Session generateSession(User user) {
        return null;
    }
}
