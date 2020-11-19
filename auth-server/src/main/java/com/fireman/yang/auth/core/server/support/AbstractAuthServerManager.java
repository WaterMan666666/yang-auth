package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.server.AuthServerManager;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public class AbstractAuthServerManager implements AuthServerManager {

    private ServerSessionDao sessionDao;

    @Override
    public List<User> getAllOlineUsers() {
        return null;
    }

    @Override
    public void logout(User user) {
        destroySession(user);
    }

    /**
     * 通过用户信息销毁session
     */
    protected void destroySession(User user){
        sessionDao.destroySession(user);
    }

}
