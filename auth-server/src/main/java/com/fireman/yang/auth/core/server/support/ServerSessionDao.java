package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.session.Session;
import com.fireman.yang.auth.core.session.SessionDao;
import com.fireman.yang.auth.core.web.utils.StringUtils;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public abstract class ServerSessionDao implements SessionDao {


    private int sessionExpire;

    private int codeExpire;

    public ServerSessionDao(int sessionExpire, int codeExpire) {
        this.sessionExpire = sessionExpire;
        this.codeExpire = codeExpire;
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
        return getKey(AuthConstants.AUTH, AuthConstants.SESSION,
                sessionType.name(), id);
    }

    protected  String getUserKey(String userId){
        return getKey(AuthConstants.AUTH, AuthConstants.ONLINE_USER, userId);
    }

    protected  String getCodeKey(String code){
        return getKey(AuthConstants.AUTH, AuthConstants.AUTH_CODE, code);
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

    public abstract void destroySession(User user);

    protected abstract void saveValue(String key, String value, int codeExpire);

    protected abstract String getValue(String key);

    public String getCode(){
        String randomCode = StringUtils.randomCode(6);
        saveValue( getCodeKey(randomCode), randomCode, codeExpire);
        return randomCode;
    }

    public boolean verifyCode(String code){
        String verifyCode = getValue(getCodeKey(code));
        return StringUtils.isNotBlank(verifyCode);
    }


}
