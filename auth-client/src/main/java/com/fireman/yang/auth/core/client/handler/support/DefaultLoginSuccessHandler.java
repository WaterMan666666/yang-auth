package com.fireman.yang.auth.core.client.handler.support;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.client.handler.LoginSuccessHandler;
import com.fireman.yang.auth.core.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tongdong
 * @Date: 2020/11/18
 * @Description:
 */
public class DefaultLoginSuccessHandler implements LoginSuccessHandler {
    @Override
    public void handler(Session session, User user, HttpServletRequest request, HttpServletResponse response) {

    }
}
