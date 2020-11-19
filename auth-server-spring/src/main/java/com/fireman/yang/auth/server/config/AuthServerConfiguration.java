package com.fireman.yang.auth.server.config;

import com.fireman.yang.auth.core.server.AuthorizeCodeFactory;
import com.fireman.yang.auth.core.server.config.AuthServerConfig;
import com.fireman.yang.auth.core.server.support.AuthorizationCodeGrantTypeProcessor;
import com.fireman.yang.auth.core.server.support.CodeResponseTypeProcessor;
import com.fireman.yang.auth.server.controller.AuthenticationController;
import com.fireman.yang.auth.server.controller.LoginController;
import com.fireman.yang.auth.server.controller.TokenController;
import com.fireman.yang.auth.server.controller.UserInfoController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author tongdong
 * @Date: 2020/11/19
 * @Description:
 */
@Configuration
public class AuthServerConfiguration {

    @Bean
    public CodeResponseTypeProcessor codeResponseTypeProcessor(AuthServerConfig config){
        return new CodeResponseTypeProcessor(config.getLoginUri(), () -> "000000");
    }

    @Bean
    public AuthorizationCodeGrantTypeProcessor authorizationCodeGrantTypeProcessor(){
        return new AuthorizationCodeGrantTypeProcessor();
    }
}
