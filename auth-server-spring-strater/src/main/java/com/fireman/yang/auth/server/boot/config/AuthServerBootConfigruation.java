package com.fireman.yang.auth.server.boot.config;

import com.fireman.yang.auth.core.server.service.AuthServerService;
import com.fireman.yang.auth.core.server.support.LocalAuthServerServiceImpl;
import com.fireman.yang.auth.core.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tongdong
 * @Date: 2020/11/4
 * @Description:
 */
@EnableConfigurationProperties(AuthServerConfigruationProperties.class)
@ConditionalOnProperty(value = "enable",prefix = "yang.auth.server", havingValue = "true")
@Configuration
//@Import(AuthClientConfiguration.class)
public class AuthServerBootConfigruation {

    private static final  Logger log = LoggerFactory.getLogger(AuthServerBootConfigruation.class);


    @Bean
    @ConditionalOnMissingBean(AuthServerService.class)
    public AuthService authService(AuthServerConfigruationProperties authProperties){
        return new LocalAuthServerServiceImpl(authProperties.getInfoBasePath());
    }



}
