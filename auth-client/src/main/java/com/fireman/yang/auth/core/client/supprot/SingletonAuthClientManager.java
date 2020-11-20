package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.dao.DefaultLocalSessionDao;
import com.fireman.yang.auth.core.client.session.ClientSessionDao;
import com.fireman.yang.auth.core.login.LoginTokenProcessor;
import com.fireman.yang.auth.core.server.AuthServerManager;
import com.fireman.yang.auth.core.session.SessionFactory;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.core.session.SessionTokenProcessor;

import java.util.List;


/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public class SingletonAuthClientManager extends AbstractAuthClientManager implements AuthServerManager {


    public SingletonAuthClientManager(AuthClientConfig config, List<SessionTokenProcessor> sessionTokenProcessors,
                                      List<LoginTokenProcessor> loginTokenProcessors,
                                      SessionFactory sessionFactory,
                                      SessionTokenFactory sessionTokenFactory,
                                      ClientSessionDao sessionDao) {
        super(config.getScop(), sessionTokenProcessors, loginTokenProcessors,
                sessionFactory, sessionTokenFactory,
                sessionDao);
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