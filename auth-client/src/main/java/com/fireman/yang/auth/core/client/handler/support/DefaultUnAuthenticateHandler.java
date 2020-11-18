package com.fireman.yang.auth.core.client.handler.support;

import com.fireman.yang.auth.core.client.handler.UnAuthenticateHandler;
import com.fireman.yang.auth.core.common.enums.ReturnCode;
import com.fireman.yang.auth.core.web.utils.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/17
 * @Description:
 */
public class DefaultUnAuthenticateHandler implements UnAuthenticateHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultUnAuthenticateHandler.class);

    public DefaultUnAuthenticateHandler(String clientId, String loginUri) {
        this.clientId = clientId;
        this.loginUri = loginUri;
    }

    private String clientId;
    private String loginUri;

    @Override
    public void handler(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("responseType","code");
        map.put("clientId", clientId);
        map.put("redirectUri", loginUri);
        response.setStatus(ReturnCode.UNLOGIN.getCode());
        responseOutWithJson(response, map);
    }

    /**
     * 以JSON格式输出
     * @param response
     */
    private void responseOutWithJson(HttpServletResponse response,
                                     Object responseObject) {
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JsonUtils.toJsonString(responseObject));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
