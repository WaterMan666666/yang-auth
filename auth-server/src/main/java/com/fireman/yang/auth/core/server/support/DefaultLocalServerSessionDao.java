package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionToken;
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
public class DefaultLocalServerSessionDao extends ServerSessionDao {

    private static final String SEPARATOR = ":";

    private static final int SESSION_MAXSIZE = 5000;

    private static final int USER_MAXSIZE = 500;

    private final Cache<String, Session> sessionCache ;

    private final Cache<String, List<SessionToken>> userCache ;

    private final Cache<String, String> keyCache ;

    public DefaultLocalServerSessionDao(int sessionExpire, int codeExpire) {
        super(sessionExpire, codeExpire);
        sessionCache = Caffeine.newBuilder().recordStats()
                .expireAfterWrite(sessionExpire, TimeUnit.SECONDS)
                .maximumSize(SESSION_MAXSIZE).build();

        userCache = Caffeine.newBuilder().recordStats()
                .expireAfterWrite(sessionExpire, TimeUnit.SECONDS)
                .maximumSize(USER_MAXSIZE).build();

        keyCache = Caffeine.newBuilder().recordStats()
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

    @Override
    protected void saveValue(String key, String value, int codeExpire) {
        keyCache.put(key, value);
    }

    @Override
    protected String getValue(String key) {
        return keyCache.getIfPresent(key);
    }

}
