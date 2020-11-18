package com.fireman.yang.auth.core.client.supprot;

import com.fireman.yang.auth.core.*;
import com.fireman.yang.auth.core.client.*;
import com.fireman.yang.auth.core.client.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.login.LoginToken;
import com.fireman.yang.auth.core.login.LoginTokenProcessor;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionFactory;
import com.fireman.yang.auth.core.session.SessionToken;
import com.fireman.yang.auth.core.session.SessionTokenFactory;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/10/29
 * @Description:
 */
public abstract class AbstractAuthClientManager implements AuthClientManager {


    public AbstractAuthClientManager(LoginScop scop, List<SessionTokenProcessor> sessionTokenProcessors,
                                     List<LoginTokenProcessor> loginTokenProcessors,
                                     SessionFactory sessionFactory,
                                     SessionTokenFactory sessionTokenFactory,
                                     ClientSessionDao sessionDao) {

        //校验参数的完整性
        // TODO
        this.scop = scop;
        this.sessionTokenProcessors = sessionTokenProcessors;
        this.loginTokenProcessors = loginTokenProcessors;
        this.sessionFactory = sessionFactory;
        this.sessionTokenFactory = sessionTokenFactory;
        this.sessionDao = sessionDao;
    }

    private LoginScop scop ;

    private List<SessionTokenProcessor> sessionTokenProcessors;

    private List<LoginTokenProcessor> loginTokenProcessors;

    private SessionFactory sessionFactory;

    private SessionTokenFactory sessionTokenFactory;

    protected ClientSessionDao sessionDao;

    @Override
    public SessionToken login(LoginToken token) {
        //检查信息
        checkLoginInfo(token);
        //判断LoginToken是否为有效Token
        User loginUser = authenticate(token);
        //登录的扩展处理
        //创建session
        Session session = createSession(token.getSessionType(), loginUser);
        //根据想要获取的token模式发送SessionToken
        return distributeSessionToken(token.getSessionType(), session);
    }

    @Override
    public Session checkLogin(SessionToken token) {
        //判断SessionToken是否为有效Token
        Session session = readSession(token);
        return session;
    }

    @Override
    public void logout(SessionToken token) {
        //判断SessionToken是否为有效Token
        Session session = checkLogin(token);
        //销毁已经存在的Session
        if(session != null) {
            destroySession(token.getType() ,session);
        }
    }

    /**
     * 检查登录信息
     */
    protected void checkLoginInfo(LoginToken token){
    }

    /**
     * 根据登录用户创建session
     */
    protected Session createSession(SessionType sessionType, User loginUser){
        //根据scop来判断完成哪种模式
        if(LoginScop.singleton.equals(scop)){
            //需要清除当前用户所有实例
            destroySession(loginUser);
        }
        Session session = sessionFactory.generateSession(loginUser);
        sessionDao.createSession(sessionType, session);
        return session;
    }

    /**
     * 获取session信息
     */
    protected  Session readSession(SessionToken token){
        //验证成功失败需要有扩展
        if(sessionTokenProcessors != null && !sessionTokenProcessors.isEmpty()){
            for (int i = 0; i < sessionTokenProcessors.size(); i++) {
                SessionTokenProcessor tokenProcessor = sessionTokenProcessors.get(i);
                if(tokenProcessor.isTypeMatch(token)){
                    return tokenProcessor.parseToken(token);
                }
            }
        }
        return null;
    }


    /**
     * 销毁session
     */
    protected void destroySession(SessionType sessionType, Session session){
        sessionDao.destroySession(sessionType, session.getId());
    }

    /**
     * 通过用户信息销毁session
     */
    protected abstract void destroySession(User user);

    /**
     * 验证用户的登录信息是否正确
     */
    protected User authenticate(LoginToken token) {
        //验证成功失败需要有扩展
        if(loginTokenProcessors != null && !loginTokenProcessors.isEmpty()){
            for (int i = 0; i < loginTokenProcessors.size(); i++) {
                LoginTokenProcessor loginTokenProcessor = loginTokenProcessors.get(i);
                if(loginTokenProcessor.isTypeMatch(token)){
                    return loginTokenProcessor.authenticate(token);
                }
            }
        }
        return null;
    }

    /**
     * 检查登录信息
     */
    protected SessionToken distributeSessionToken(SessionType sessionType, Session session){
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken(sessionType, session);
        return sessionToken;
    }
}
