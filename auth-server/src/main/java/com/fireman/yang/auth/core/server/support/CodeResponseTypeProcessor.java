package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.common.ThreadContext;
import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.ResponseType;
import com.fireman.yang.auth.core.exception.SystemErrorException;
import com.fireman.yang.auth.core.server.AuthorizeCodeFactory;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;
import com.fireman.yang.auth.core.web.utils.CollectionUtils;
import com.fireman.yang.auth.core.web.utils.url.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public class CodeResponseTypeProcessor extends ResponseTypeProcessor {

    private static final Logger log = LoggerFactory.getLogger(CodeResponseTypeProcessor.class);

    public CodeResponseTypeProcessor(AuthorizeCodeFactory authorizeCodeFactory) {
        this.authorizeCodeFactory = authorizeCodeFactory;
    }

    private AuthorizeCodeFactory authorizeCodeFactory;
    @Override
    public boolean isTypeMatch(ResponseType responseType) {
        return responseType == ResponseType.code;
    }

    @Override
    public void processAuthenticate(AuthorizeDTO authorizeDTO, AppClientDTO appClientDTO) {
        //获取授权码
        String authorizeCode = generateAuthorizeCode();
        String domain = appClientDTO.getDomain();
        String redirectUri = authorizeDTO.getRedirectUri();
        String originUrl = authorizeDTO.getOriginUrl();
        //重定向到回调地址
        Map<String, String> params = new HashMap<>(2);
        CollectionUtils.putString(params, "code" , authorizeCode);
        CollectionUtils.putString(params, "originUrl" , originUrl);
        String callUrl = UrlUtils.genUrl(domain, Arrays.asList(redirectUri) , params);
        redirect(callUrl);
    }


    @Override
    public void processUnauthenticate(AuthorizeDTO authorizeDTO, AppClientDTO appClientDTO) {
        //根据是否自定义登录页面，如果没有则重定向到默认的登录页面
        String domain = appClientDTO.getDomain();
        String loginUrl = authorizeDTO.getLoginUrl();
        String originUrl = authorizeDTO.getOriginUrl();
        Map<String, String> params = new HashMap<>(2);
        CollectionUtils.putString(params, "originUrl" , originUrl);
        String callUrl = UrlUtils.genUrl(domain, Arrays.asList(loginUrl) , params);
        redirect(callUrl);
    }

    private String generateAuthorizeCode() {
        return authorizeCodeFactory.generate();
    }

    private void redirect(String url) {
        HttpServletResponse response =ThreadContext.getResponse();
        if(response != null) {
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                log.error("重定向报错", e);
            }
        }else{
            throw new SystemErrorException("获取响应对象失败");
        }
    }
}
