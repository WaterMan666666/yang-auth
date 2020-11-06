package com.fireman.yang.auth.core.client;

import com.fireman.yang.auth.core.User;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface SingletonClientSessionDao extends ClientSessionDao{

    void destroySession(User user);

}
