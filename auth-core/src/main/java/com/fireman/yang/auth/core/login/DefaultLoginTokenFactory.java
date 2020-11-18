package com.fireman.yang.auth.core.login;

import com.fireman.yang.auth.core.common.ThreadContext;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.exception.ParameterErrorException;
import com.fireman.yang.auth.core.web.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tongdong
 * @Date: 2020/11/10
 * @Description:
 */
public class DefaultLoginTokenFactory implements LoginTokenFactory {

    @Override
    public LoginToken generateLoginToken() {
        if(isPasswordToken()){
            return getPasswordToken();
        }else if(isAuthorizeCodeToken()){
            return getAuthorizeCodeToken();
        }
        throw  new ParameterErrorException("login params is not exist");
    }


    protected boolean isPasswordToken(){
        HttpServletRequest request = ThreadContext.getRequest();
        String username = request.getParameter(AuthConstants.USERNAME);
        String password = request.getParameter(AuthConstants.PASSWORD);
        String loginType = request.getParameter(AuthConstants.LOGIN_TYPE);
        SessionType sessionType = SessionType.toEnum(loginType);
        return StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) && sessionType != null;
    }

    protected LoginToken getPasswordToken(){
        HttpServletRequest request = ThreadContext.getRequest();
        String username = request.getParameter(AuthConstants.USERNAME);
        String password = request.getParameter(AuthConstants.PASSWORD);
        String loginType = request.getParameter(AuthConstants.LOGIN_TYPE);
        SessionType sessionType = SessionType.toEnum(loginType);
        PasswordToken passwordToken = new PasswordToken(username, password, sessionType == null ? SessionType.AccessToken : sessionType);
        return passwordToken;
    }

    protected boolean isAuthorizeCodeToken(){
        HttpServletRequest request = ThreadContext.getRequest();
        String code = request.getParameter(AuthConstants.CODE);
        String loginType = request.getParameter(AuthConstants.LOGIN_TYPE);
        SessionType sessionType = SessionType.toEnum(loginType);
        return StringUtils.isNotBlank(code)  && sessionType != null;
    }

    protected LoginToken getAuthorizeCodeToken(){
        HttpServletRequest request = ThreadContext.getRequest();
        String code = request.getParameter(AuthConstants.CODE);
        String loginType = request.getParameter(AuthConstants.LOGIN_TYPE);
        SessionType sessionType = SessionType.toEnum(loginType);
        AuthorizeCodeToken authorizeCodeToken = new AuthorizeCodeToken(code, sessionType == null ? SessionType.AccessToken : sessionType);
        return authorizeCodeToken;
    }
}
