package com.fireman.yang.auth.core.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName CookieUtils
 * @Author TD
 * @Date 2019/1/30 16:47
 * @Description 
 */
public class CookieUtils {


    public static String getValue(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        if(cookies == null || StringUtils.isBlank(key)){
            return null;
        }
        for(Cookie cookie : cookies){
            if(key.equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }


    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String key, String value){
        if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)){
            Cookie[] cookiesReqs = request.getCookies();
            if(cookiesReqs != null){
                for (Cookie cookie : cookiesReqs){
                    if(key.equals(cookie.getName())){
                        cookie.setValue(value);
                        response.addCookie(cookie);
                        return;
                    }
                }
            }
            Cookie cookieNew = new Cookie(key,value);
            response.addCookie(cookieNew);
            }

    }

    public static void addCookies(HttpServletRequest request, HttpServletResponse response, Map<String, String> cookies){
        if(cookies != null && !cookies.isEmpty()){
            for (Map.Entry<String, String> entry : cookies.entrySet()){
                boolean flag = false;
                Cookie[] cookiesReqs = request.getCookies();
                if(cookiesReqs != null){
                    for (Cookie cookie : cookiesReqs){
                        if(entry.getKey().equals(cookie.getName())){
                            cookie.setValue(entry.getValue());
                            flag = true;
                            response.addCookie(cookie);
                            break;
                        }
                    }
                }
                if(!flag) {
                    Cookie cookieNew = new Cookie(entry.getKey(), entry.getValue());
                    response.addCookie(cookieNew);
                }
            }
        }
    }
}
