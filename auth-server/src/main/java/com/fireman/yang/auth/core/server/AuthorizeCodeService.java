package com.fireman.yang.auth.core.server;

/**
 * @author tongdong
 * @Date: 2020/11/11
 * @Description:
 */
public interface AuthorizeCodeService {

    String generate();


    boolean verify(String code);
}
