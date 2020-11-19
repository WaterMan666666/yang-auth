package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.exception.AuthException;
import com.fireman.yang.auth.core.server.AuthSsoServerManager;
import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;
import com.fireman.yang.auth.core.server.service.AuthServerCoreService;
import com.fireman.yang.auth.core.session.SessionToken;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
@Controller
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthSsoServerManager serverManager;

    @Autowired
    private SessionTokenFactory sessionTokenFactory;

    @Autowired
    private AuthServerCoreService authServerCoreService;

    /**
     * 服务授权
     * 1.校验数据正确性
     * 2.根据response_type 区分走的授权方式  目前只支持code
     */
    @RequestMapping(value = "oauth/sso/authorize", method = RequestMethod.GET)
    public String authorize(@RequestParam(value = "response_type", required = false) String responseType,
                          @RequestParam(value = "client_id", required = false) String clientId,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri) throws AuthException {

        AuthorizeDTO authorizeDTO = new AuthorizeDTO(responseType, clientId, redirectUri);
        checkInfo(authorizeDTO);
        //校验生成数据是否健全
        boolean ssoAuthenticate = ssoAuthenticate();
        String url =  authServerCoreService.authorize(authorizeDTO, ssoAuthenticate);
        return "redirect:" + url;
    }

    private void checkInfo(AuthorizeDTO authorizeDTO) {

    }

    private boolean ssoAuthenticate() {
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken();
        return serverManager.checkLogin(sessionToken) != null;
    }
}
