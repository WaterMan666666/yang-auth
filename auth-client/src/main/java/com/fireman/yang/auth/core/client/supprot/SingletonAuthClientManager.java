package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.dao.DefaultLocalSessionDao;


/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public class SingletonAuthClientManager extends AbstractAuthClientManager {


    public SingletonAuthClientManager(AuthClientConfig config){
        super(config.getScop(), config.getSessionTokenProcessors(), config.getLoginTokenProcessors(),
                config.getSessionFactory(), config.getSessionTokenFactory(),
                new DefaultLocalSessionDao(config.getClientId(), config.getSessionExpire()));
    }

    @Override
    protected void destroySession(User user) {
        DefaultLocalSessionDao dao = (DefaultLocalSessionDao)sessionDao;
        dao.destroySession(user);
    }
}
