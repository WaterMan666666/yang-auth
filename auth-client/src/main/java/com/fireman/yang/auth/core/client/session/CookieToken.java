package com.fireman.yang.auth.core.client.session;


import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.web.utils.CookieUtils;
import com.fireman.yang.auth.core.web.utils.StringUtils;
import com.fireman.yang.auth.core.web.utils.WebUtils;
import com.fireman.yang.auth.session.SessionToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class CookieToken extends SessionToken {

    private String clientId;

    public CookieToken(String clientId, String token){
        super(token, SessionType.Cookie);
        this.clientId = clientId;
    }

    @Override
    public void afterLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //在response中添加cookie
        CookieUtils.addCookie(request, response , getCookieKey(clientId), getToken());
        String originUrl = WebUtils.getRequestParam(request, AuthConstants.ORIGIN_URL);
        response.sendRedirect(StringUtils.isBlank(originUrl) ? "/" : originUrl);
    }

    public static String getCookieKey(String clientId)  {
        return clientId + "_SID";
    }
}
