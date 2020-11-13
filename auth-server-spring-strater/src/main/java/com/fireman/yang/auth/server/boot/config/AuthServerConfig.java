package com.fireman.yang.auth.server.boot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
public class AuthServerConfig {

    private static final  Logger log = LoggerFactory.getLogger(AuthServerConfig.class);



}
