package com.fireman.yang.auth.server.config;

import com.fireman.yang.auth.core.server.AuthorizeCodeService;
import com.fireman.yang.auth.core.server.config.AuthServerConfig;
import com.fireman.yang.auth.core.server.support.AuthorizationCodeGrantTypeProcessor;
import com.fireman.yang.auth.core.server.support.CodeResponseTypeProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tongdong
 * @Date: 2020/11/19
 * @Description:
 */
@Configuration
public class AuthServerConfiguration {

    @Bean
    public CodeResponseTypeProcessor codeResponseTypeProcessor(AuthServerConfig config, AuthorizeCodeService authorizeCodeService){
        return new CodeResponseTypeProcessor(config.getLoginUri(), authorizeCodeService);
    }

    @Bean
    public AuthorizationCodeGrantTypeProcessor authorizationCodeGrantTypeProcessor(){
        return new AuthorizationCodeGrantTypeProcessor();
    }
}
