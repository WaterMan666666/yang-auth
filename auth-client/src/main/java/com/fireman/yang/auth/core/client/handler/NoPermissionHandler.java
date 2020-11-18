package com.fireman.yang.auth.core.client.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tongdong
 * @Date: 2020/11/17
 * @Description:
 */
public interface NoPermissionHandler {

    /**
     * 验证未登录时执行该方法
     */
    void handler(HttpServletRequest request, HttpServletResponse response);
}
