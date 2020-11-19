package com.fireman.yang.auth.core.session;

import com.fireman.yang.auth.core.common.enums.SessionType;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface SessionDao {



    Session readSession(SessionType sessionType, String sessionId);

    void createSession(SessionType sessionType, Session session);

    void destroySession(SessionType sessionType, String sessionId);

}
