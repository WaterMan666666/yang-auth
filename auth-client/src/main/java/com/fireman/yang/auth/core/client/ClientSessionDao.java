package com.fireman.yang.auth.core.client;

import com.fireman.yang.auth.core.common.enums.SessionType;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface ClientSessionDao {



    Session readSession(SessionType sessionType, String sessionId);

    void createSession(SessionType sessionType, Session session);

    void destroySession(SessionType sessionType, String sessionId);

}
