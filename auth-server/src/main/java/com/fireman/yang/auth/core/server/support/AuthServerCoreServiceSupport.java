package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.common.enums.GrantType;
import com.fireman.yang.auth.core.common.enums.ResponseType;
import com.fireman.yang.auth.core.exception.ParameterErrorException;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;
import com.fireman.yang.auth.core.server.dto.GrantTokenDTO;
import com.fireman.yang.auth.core.server.service.AuthServerCoreService;
import com.fireman.yang.auth.core.server.service.AuthServerService;
import com.fireman.yang.auth.core.web.utils.StringUtils;
import com.fireman.yang.auth.core.session.SessionToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/17
 * @Description:
 */
public class AuthServerCoreServiceSupport implements AuthServerCoreService {

    private static final Logger log = LoggerFactory.getLogger(AuthServerCoreServiceSupport.class);


    public AuthServerCoreServiceSupport(List<ResponseTypeProcessor> responseTypeProcessors, List<GrantTypeProcessor> grantTypeProcessors, AuthServerService authServiceService) {
        this.responseTypeProcessors = responseTypeProcessors;
        this.grantTypeProcessors = grantTypeProcessors;
        this.authServiceService = authServiceService;
    }

    public List<ResponseTypeProcessor> getResponseTypeProcessors() {
        return responseTypeProcessors;
    }

    public void setResponseTypeProcessors(List<ResponseTypeProcessor> responseTypeProcessors) {
        this.responseTypeProcessors = responseTypeProcessors;
    }

    public List<GrantTypeProcessor> getGrantTypeProcessors() {
        return grantTypeProcessors;
    }

    public void setGrantTypeProcessors(List<GrantTypeProcessor> grantTypeProcessors) {
        this.grantTypeProcessors = grantTypeProcessors;
    }

    public AuthServerService getAuthServiceService() {
        return authServiceService;
    }

    public void setAuthServiceService(AuthServerService authServiceService) {
        this.authServiceService = authServiceService;
    }

    private List<ResponseTypeProcessor> responseTypeProcessors;

    private List<GrantTypeProcessor> grantTypeProcessors;

    private AuthServerService authServiceService;

    @Override
    public String authorize(AuthorizeDTO authorizeDTO,  boolean isAuthenticate) {

        String responseType = authorizeDTO.getResponseType();
        ResponseType responseTypeEnum = ResponseType.toEnum(responseType);
        //根据ResponseType来选择授权方式
        if(responseTypeProcessors != null && !responseTypeProcessors.isEmpty()){
            for (int i = 0; i < responseTypeProcessors.size(); i++) {
                ResponseTypeProcessor typeProcessor = responseTypeProcessors.get(i);
                if(typeProcessor.isTypeMatch(responseTypeEnum)){
                    AppClientDTO clientDTO = checkAppClient(authorizeDTO.getClientId());
                    if(isAuthenticate){
                       return  typeProcessor.processAuthenticate(authorizeDTO, clientDTO);
                    }else {
                       return typeProcessor.processUnauthenticate(authorizeDTO, clientDTO);
                    }
                }
            }
        }
        return "/";
    }

    @Override
    public SessionToken grantToken(GrantTokenDTO grantTokenDTO) {
        String grantType = grantTokenDTO.getGrantType();
        //获取用户登录凭证
        GrantType grantTypeEnum = GrantType.toEnum(grantType);
        //根据ResponseType来选择授权方式
        if(grantTypeProcessors != null && !grantTypeProcessors.isEmpty()){
            for (int i = 0; i < grantTypeProcessors.size(); i++) {
                GrantTypeProcessor grantTypeProcessor = grantTypeProcessors.get(i);
                if(grantTypeProcessor.isTypeMatch(grantTypeEnum)){
                    return grantTypeProcessor.grantToken(checkAppClient(grantTokenDTO.getClientId()), grantTokenDTO);
                }
            }
        }
        return null;
    }

    private AppClientDTO checkAppClient(String clientId) {
        if (StringUtils.isBlank(clientId)) {
            //参数检验失败
            log.info("clientId, 参数校验失败：{}", clientId);
            throw new ParameterErrorException("bad param ClientId");
        }
        //校验ClientId是否存在
        AppClientDTO appClient = authServiceService.getAppClient(clientId);
        if (appClient == null) {
            //参数检验失败
            log.info("校验ClientId不存在: {}", clientId);
            throw new ParameterErrorException("ClientId not exist");
        }
        return appClient;
    }
}
