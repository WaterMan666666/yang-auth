package com.fireman.yang.auth.core.client;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.session.ClientSessionDao;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface SingletonClientSessionDao {

    void destroySession(User user);

}
