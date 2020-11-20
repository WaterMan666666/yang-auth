package com.fireman.yang.auth.client.test.config;

import com.fireman.yang.auth.client.test.handler.RedirectHandler;
import com.fireman.yang.auth.core.client.AuthClientManager;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.filter.SsoAuthenticationFilter;
import com.fireman.yang.auth.core.client.handler.UnAuthenticateHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TestConfigruation
 * @Author DYB
 * @Date 2020/11/18 17:48
 * @Description
 * @Version V1.0
 */
@Configuration
public class TestConfigruation {


    @Bean
    public RedirectHandler redirectHandler (AuthClientConfig config) {
        return new RedirectHandler(config.getClientId(), config.getLoginUri());
    }
}
