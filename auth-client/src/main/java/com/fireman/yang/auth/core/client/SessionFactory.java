package com.fireman.yang.auth.core.client;

import com.fireman.yang.auth.core.User;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description:
 */
public interface SessionFactory {


    Session generateSession(User user);
}
