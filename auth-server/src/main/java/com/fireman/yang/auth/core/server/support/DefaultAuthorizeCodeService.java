package com.fireman.yang.auth.core.server.support;

import com.fireman.yang.auth.core.server.AuthorizeCodeService;

/**
 * @author tongdong
 * @Date: 2020/11/20
 * @Description:
 */
public class DefaultAuthorizeCodeService implements AuthorizeCodeService {

    public DefaultAuthorizeCodeService(ServerSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    private ServerSessionDao sessionDao;

    @Override
    public String generate() {
        return sessionDao.getCode();
    }

    @Override
    public boolean verify(String code) {
        return sessionDao.verifyCode(code);
    }
}
