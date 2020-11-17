package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.common.Processor;
import com.fireman.yang.auth.core.common.enums.GrantType;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.GrantTokenDTO;
import com.fireman.yang.auth.session.SessionToken;

/**
 * @author tongdong
 * @Date: 2020/11/16
 * @Description:
 */
public abstract class GrantTypeProcessor implements Processor<GrantType> {

    public abstract SessionToken grantToken(AppClientDTO clientDTO, GrantTokenDTO grantTokenDTO);
}
