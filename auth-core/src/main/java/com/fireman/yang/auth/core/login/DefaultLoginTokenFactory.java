package com.fireman.yang.auth.core.login;

import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.SessionType;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tongdong
 * @Date: 2020/11/10
 * @Description:
 */
public class DefaultLoginTokenFactory implements LoginTokenFactory {

    @Override
    public LoginToken generateLoginToken(HttpServletRequest request) {
        String username = request.getParameter(AuthConstants.USERNAME);
        String password = request.getParameter(AuthConstants.PASSWORD);
        String loginType = request.getParameter(AuthConstants.LOGIN_TYPE);
        SessionType sessionType = SessionType.toEnum(loginType);
        PasswordToken passwordToken = new PasswordToken(username, password, sessionType == null ? SessionType.AccessToken : sessionType);
        return passwordToken;
    }
}
