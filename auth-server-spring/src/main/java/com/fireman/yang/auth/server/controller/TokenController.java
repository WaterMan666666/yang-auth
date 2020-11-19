package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.server.dto.GrantTokenDTO;
import com.fireman.yang.auth.core.server.service.AuthServerCoreService;
import com.fireman.yang.auth.core.session.SessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
@Controller
public class TokenController {


    @Autowired
    private AuthServerCoreService authServerCoreService;
    /**
     * 登录提交
     * @return
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    public void getToken(@RequestParam(value = "grant_type", required = false) String grantType,
                          @RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "client_id", required = false) String clientId){
        GrantTokenDTO grantTokenDTO = new GrantTokenDTO(grantType, code, redirectUri, clientId);
        SessionToken sessionToken = authServerCoreService.grantToken(grantTokenDTO);

    }
}
