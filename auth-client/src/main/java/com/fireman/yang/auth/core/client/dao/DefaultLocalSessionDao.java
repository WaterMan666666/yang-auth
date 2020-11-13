package com.fireman.yang.auth.core.client.dao;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.SingletonClientSessionDao;
import com.fireman.yang.auth.core.client.session.AbstractSessionDao;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.session.Session;
import com.fireman.yang.auth.session.SessionToken;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 内存中的实现方式，建议不超过5000个用户
 */
public class DefaultLocalSessionDao extends AbstractSessionDao implements SingletonClientSessionDao {

    private static final String SEPARATOR = ":";

    private static final int SESSION_MAXSIZE = 5000;

    private static final int USER_MAXSIZE = 500;

    private final Cache<String, Session> sessionCache ;

    private final Cache<String, List<SessionToken>> userCache ;

    public DefaultLocalSessionDao(String clientId, int sessionExpire) {
        super(clientId, sessionExpire);
        sessionCache = Caffeine.newBuilder().recordStats()
                .expireAfterWrite(sessionExpire, TimeUnit.SECONDS)
                .maximumSize(SESSION_MAXSIZE).build();

        userCache = Caffeine.newBuilder().recordStats()
                .expireAfterWrite(sessionExpire, TimeUnit.SECONDS)
                .maximumSize(USER_MAXSIZE).build();
    }


    @Override
    protected String getSeparator() {
        return SEPARATOR;
    }

    @Override
    protected Session readSession(String key) {
        return sessionCache.getIfPresent(key);
    }

    @Override
    protected void createSession(String key, SessionType sessionType, Session session) {
        User user = session.getUser();
        String userKey = getUserKey(user.getId());
        List<SessionToken> userTokenList = userCache.getIfPresent(userKey);
        if(userTokenList == null){
            ArrayList<SessionToken> sessionTokens = new ArrayList<>();
            sessionTokens.add(new SessionToken(session.getId(), sessionType));
            userCache.put(userKey, sessionTokens);
        }else {
            userTokenList.add(new SessionToken(session.getId(), sessionType));
            userCache.put(userKey, userTokenList);
        }
        sessionCache.put(key, session);
    }

    @Override
    protected void destroySession(String key) {
        sessionCache.invalidate(key);
    }

    @Override
    public void destroySession(User user){
        String userKey = getUserKey(user.getId());
        List<SessionToken> userTokenList = userCache.getIfPresent(userKey);
        if(userTokenList != null){
            for (SessionToken token : userTokenList){
                destroySession(getSessionKey(token.getType(), token.getToken()));
            }
        }
        userCache.invalidate(user);
    }
}
