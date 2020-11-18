package com.fireman.yang.auth.core.client.handler;

import com.fireman.yang.auth.core.User;
import com.fireman.yang.auth.core.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tongdong
 * @Date: 2020/11/17
 * @Description:
 */
public interface LoginSuccessHandler {

    /**
     * 登录成功后执行该方法
     */
    void handler(Session session, User user, HttpServletRequest request, HttpServletResponse response);
}
