package com.fireman.yang.auth.core.server;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public interface ServerSessionDao {



    ServerSession readSession(String sessionId);

    void createSession(ServerSession session);

    void destroySession(String sessionId);

    List<String> readSessionForKey(String key);

    void addSessionForKey(String key, String sessionId);

}
