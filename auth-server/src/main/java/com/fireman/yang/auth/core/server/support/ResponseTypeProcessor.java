package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.common.Processor;
import com.fireman.yang.auth.core.common.enums.ResponseType;
import com.fireman.yang.auth.core.server.dto.AppClientDTO;
import com.fireman.yang.auth.core.server.dto.AuthorizeDTO;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public abstract class ResponseTypeProcessor implements Processor<ResponseType> {

    /**
     * 处理登录后得情况
     */
    public abstract String processAuthenticate(AuthorizeDTO authorizeDTO, AppClientDTO appClientDTO);
    /**
     * 处理未登录时得情况
     */
    public abstract String processUnauthenticate(AuthorizeDTO authorizeDTO, AppClientDTO appClientDTO);

}
