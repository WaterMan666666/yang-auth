package com.fireman.yang.auth.client.dao;

import com.fireman.yang.auth.core.client.ClientSessionDao;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tongdong
 * @Date: 2020/6/28
 * @Description: 内存中的实现方式，建议不超过5000个用户
 */
public class DefaultLocalSessionDao implements ClientSessionDao {

    private static final int SESSION_MAXSIZE = 5000;

    private static final int USER_MAXSIZE = 500;

    private final Cache<String, Session> cache ;

    private final Cache<String, List<String>> cacheKey ;

    public DefaultLocalSessionDao(int sessionExpireTime) {
        cache = Caffeine.newBuilder().recordStats()
                .expireAfterWrite(sessionExpireTime, TimeUnit.SECONDS)
                .maximumSize(SESSION_MAXSIZE).build();

        cacheKey = Caffeine.newBuilder().recordStats()
                .expireAfterWrite(sessionExpireTime, TimeUnit.SECONDS)
                .maximumSize(USER_MAXSIZE).build();
    }

    @Override
    public Session readSession(SessionType sessionType, String sessionId) {
        return null;
    }

    @Override
    public void createSession(SessionType sessionType, Session session) {

    }

    @Override
    public void destroySession(SessionType sessionType, String sessionId) {

    }

//    @Override
//    public Session readSession(String sessionId) {
//        return cache.getIfPresent(sessionId);
//    }
//
//    @Override
//    public void createSession(Session session) {
//        String id = session.getId();
//        cache.put(id, session);
//    }
//    @Override
//    public void destroySession(String sessionId) {
//        cache.invalidate(sessionId);
//    }

}
