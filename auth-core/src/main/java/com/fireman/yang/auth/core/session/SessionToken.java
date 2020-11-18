package com.fireman.yang.auth.core.session;

import com.fireman.yang.auth.core.common.Token;
import com.fireman.yang.auth.core.common.enums.SessionType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Transient;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class SessionToken implements Token {

    /** token真实值 */
    private String token;

    /** token的类型 */
    private SessionType type;


    public SessionToken(String token, SessionType type) {
        this.token = token;
        this.type = type;
    }
    @Transient
    @Override
    public String getToken() {
        return token;
    }
    @Transient
    public SessionType getType() {
        return type;
    }

    public void setType(SessionType type) {
        this.type = type;
    }

    public void  afterLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
