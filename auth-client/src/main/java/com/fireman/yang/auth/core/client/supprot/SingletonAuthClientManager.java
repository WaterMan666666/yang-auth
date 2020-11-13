package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.dao.DefaultLocalSessionDao;
import com.fireman.yang.auth.core.server.AuthServerManager;

import java.util.List;


/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public class SingletonAuthClientManager extends AbstractAuthClientManager implements AuthServerManager {


    public SingletonAuthClientManager(AuthClientConfig config) {
        super(config.getScop(), config.getSessionTokenProcessors(), config.getLoginTokenProcessors(),
                config.getSessionFactory(), config.getSessionTokenFactory(),
                config.getSessionDao());
    }

    @Override
    public List<User> getAllOlineUsers() {
        return null;
    }

    @Override
    public void logout(User user) {
        DefaultLocalSessionDao dao = (DefaultLocalSessionDao) sessionDao;
        dao.destroySession(user);
    }

    @Override
    protected void destroySession(User user) {
        logout(user);
    }
}