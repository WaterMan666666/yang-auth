package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.common.enums.GrantType;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.GrantTokenDTO;
import com.fireman.yang.auth.core.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/11/19
 * @Description:
 */
public class AuthorizationCodeGrantTypeProcessor extends GrantTypeProcessor {

    @Override
    public SessionToken grantToken(AppClientDTO clientDTO, GrantTokenDTO grantTokenDTO) {
        return null;
    }

    @Override
    public boolean isTypeMatch(GrantType grantType) {
        return GrantType.authorization_code == grantType;
    }
}
