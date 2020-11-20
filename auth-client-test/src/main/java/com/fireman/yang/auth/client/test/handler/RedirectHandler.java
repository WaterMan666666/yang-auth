package com.fireman.yang.auth.client.test.handler;

import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.filter.SsoAuthenticationFilter;
import com.fireman.yang.auth.core.client.handler.UnAuthenticateHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName RedirectHandler
 * @Author DYB
 * @Date 2020/11/18 17:44
 * @Description
 * @Version V1.0
 */
public class RedirectHandler implements UnAuthenticateHandler {
    public RedirectHandler(String clientId, String loginUri) {
        this.clientId = clientId;
        this.loginUri = loginUri;
    }

    private String clientId;
    private String loginUri;
    @Override
    public void handler(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("http://10.236.122.223:8080/oauth/sso/authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + loginUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
