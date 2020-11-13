package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.common.enums.ResponseType;
import com.fireman.yang.auth.core.exception.AuthException;
import com.fireman.yang.auth.core.exception.ParameterErrorException;
import com.fireman.yang.auth.core.server.AuthSsoServerManager;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;
import com.fireman.yang.auth.core.server.service.AuthServiceService;
import com.fireman.yang.auth.core.server.support.ResponseTypeProcessor;
import com.fireman.yang.auth.server.utils.validate.ValidateUtils;
import com.fireman.yang.auth.session.SessionToken;
import com.fireman.yang.auth.session.SessionTokenFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
@Controller
@RequestMapping(value = "oauth2")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthSsoServerManager serverManager;

    @Autowired
    private AuthServiceService authServiceService;

    @Autowired
    private List<ResponseTypeProcessor> responseTypeProcessors;

    @Autowired
    private SessionTokenFactory sessionTokenFactory;
    /**
     * 服务授权
     * 1.校验数据正确性
     * 2.根据response_type 区分走的授权方式  目前只支持code
     */
    @RequestMapping(value = "{bizType}/sso/authorize", method = RequestMethod.GET)
    public void authorize(@RequestParam(value = "response_type", required = false) String responseType,
                          @RequestParam(value = "client_id", required = false) String clientId,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "login_url", required = false) String loginUrl,
                          @PathVariable(name = "bizType", required = false) String bizType) throws AuthException {


        AuthorizeDTO authorizeDTO = new AuthorizeDTO(responseType, clientId, redirectUri);
        authorizeDTO.setLoginUrl(loginUrl);
        //校验生成数据是否健全
        AppClientDTO clientDTO = checkInfo(authorizeDTO);
        boolean ssoAuthenticate = ssoAuthenticate();
        ResponseType responseTypeEnum = ResponseType.toEnum(responseType);
        //根据ResponseType来选择授权方式
        if(responseTypeProcessors != null && !responseTypeProcessors.isEmpty()){
            for (int i = 0; i < responseTypeProcessors.size(); i++) {
                ResponseTypeProcessor typeProcessor = responseTypeProcessors.get(i);
                if(typeProcessor.isTypeMatch(responseTypeEnum)){
                    if(ssoAuthenticate){
                        typeProcessor.processAuthenticate(authorizeDTO, clientDTO);
                    }else {
                        typeProcessor.processUnauthenticate(authorizeDTO, clientDTO);
                    }
                }
            }
        }
    }

    private boolean ssoAuthenticate() {
        SessionToken sessionToken = sessionTokenFactory.generateSessionToken();
        return serverManager.checkLogin(sessionToken) != null;
    }

    private AppClientDTO checkInfo(AuthorizeDTO authorizeDTO) {
        String validateResult = ValidateUtils.validate(authorizeDTO);
        if (!AuthConstants.COMMON_SUCCESS.equals(validateResult)) {
            //参数检验失败
            log.info("服务授权, 参数校验失败：{}", validateResult);
            throw new ParameterErrorException(validateResult);
        }
        //校验ClientId是否存在
        AppClientDTO appClient = authServiceService.getAppClient(authorizeDTO.getClientId());
        if (appClient == null) {
            //参数检验失败
            log.info("服务授权, 参数校验失败：ClientId不存在: {}", authorizeDTO.getClientId());
            throw new ParameterErrorException("ClientId不存在");
        }
        return appClient;
    }
}
