package com.fireman.yang.auth.core.client.session;


import com.fireman.yang.auth.core.common.enums.SessionType;
import com.fireman.yang.auth.core.web.utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tongdong
 * @Date: 2020/6/9
 * @Description:
 */
public class CookieToken extends SessionToken {

    public CookieToken(HttpServletRequest request, String clientId){
        super(CookieUtils.getValue(request, clientId + "_SID"), SessionType.Cookie);
    }
}
