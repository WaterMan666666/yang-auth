package com.fireman.yang.auth.server.controller;

import com.fireman.yang.auth.core.common.enums.GrantType;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.GrantTokenDTO;
import com.fireman.yang.auth.core.server.support.GrantTypeProcessor;
import com.fireman.yang.auth.session.SessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
@Controller
public class TokenController {


    @Autowired
    private List<GrantTypeProcessor> grantTypeProcessors;

    /**
     * 登录提交
     * @return
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public void getToken(@RequestParam(value = "grant_type", required = false) String grantType,
                          @RequestParam(value = "code", required = false) String code,
                          @RequestParam(value = "redirect_uri", required = false) String redirectUri,
                          @RequestParam(value = "client_id", required = false) String clientId,
                          HttpServletRequest requset){
        GrantTokenDTO grantTokenDTO = new GrantTokenDTO(code, redirectUri, clientId);
        //校验信息的完整
        AppClientDTO clientDTO = checkInfo(grantTokenDTO);
        //获取用户登录凭证
        GrantType grantTypeEnum = GrantType.toEnum(grantType);
        //根据ResponseType来选择授权方式
        if(grantTypeProcessors != null && !grantTypeProcessors.isEmpty()){
            for (int i = 0; i < grantTypeProcessors.size(); i++) {
                GrantTypeProcessor grantTypeProcessor = grantTypeProcessors.get(i);
                if(grantTypeProcessor.isTypeMatch(grantTypeEnum)){
                    grantTypeProcessor.grantToken(clientDTO, grantTokenDTO);
                    break;
                }
            }
        }
    }

    private AppClientDTO checkInfo(GrantTokenDTO grantTokenDTO) {
        return null;
    }
}
