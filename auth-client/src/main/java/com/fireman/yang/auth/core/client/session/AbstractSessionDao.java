package com.fireman.yang.auth.core.client.session;

import com.fireman.yang.auth.core.client.ClientSessionDao;
import com.fireman.yang.auth.core.client.Session;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.SessionType;


/**
 * @author tongdong
 * @Date: 2020/10/30
 * @Description:
 */
public abstract class AbstractSessionDao implements ClientSessionDao {

    private String clientId;

    private int sessionExpire;

    public AbstractSessionDao(String clientId, int sessionExpire) {
        this.clientId = clientId;
        this.sessionExpire = sessionExpire;
    }

    @Override
    public Session readSession(SessionType sessionType, String id) {
        return readSession(getSessionKey(sessionType, id));
    }

    @Override
    public void createSession(SessionType sessionType, Session session) {
        String id = session.getId();
        createSession(getSessionKey(sessionType, id), sessionType, session);
    }

    @Override
    public void destroySession(SessionType sessionType, String id) {
        destroySession(getSessionKey(sessionType, id));
    }

    protected abstract String getSeparator();

    protected  String getSessionKey(SessionType sessionType, String id){
         return getKey(AuthConstants.UCENTER, AuthConstants.SESSION,
                clientId, sessionType.name(), id);
    }

    protected  String getUserKey(String userId){
        return getKey(AuthConstants.UCENTER, clientId, AuthConstants.ONLINE_USER,userId);
    }

    private  String getKey(String... keys){
        if(keys != null && keys.length > 0){
            String separator = getSeparator();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < keys.length - 1; i++) {
                String key = keys[i];
                sb.append(key).append(separator);
            }
            sb.append(keys[keys.length - 1]);
            return sb.toString();
        }
        return null;
    }


    protected abstract Session readSession(String key);

    protected abstract void createSession(String key, SessionType sessionType, Session session);

    protected abstract void destroySession(String key);
}
