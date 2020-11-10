package com.fireman.yang.auth.core.client.session;


import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static com.fireman.yang.auth.core.common.constants.AuthConstants.AUTHORIZATION;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class AccessToken extends SessionToken {


    public AccessToken(HttpServletRequest request) {
        super(getAuthorizationToken(request), SessionType.AccessToken);
    }

    public AccessToken(String token) {
        super(token, SessionType.AccessToken);
    }

    /**
     * 从header中获取token
     */
    private static String getAuthorizationToken(HttpServletRequest httpRequest){
        //header中获取
        String authorizationHeader = httpRequest.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || authorizationHeader.length() == 0) {
            return null;
        }
        String[] authTokens = authorizationHeader.split(" ");
        if (authTokens == null || authTokens.length < 2) {
            return null;
        }
        return authTokens[1];
    }

    @Override
    public void afterLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print(JsonUtils.toJsonString(this));
    }
}
