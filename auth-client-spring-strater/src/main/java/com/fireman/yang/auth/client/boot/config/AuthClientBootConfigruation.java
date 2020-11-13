package com.fireman.yang.auth.client.boot.config;

import com.fireman.yang.auth.client.config.AuthClientConfiguration;
import com.fireman.yang.auth.core.client.*;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.dao.DefaultLocalSessionDao;
import com.fireman.yang.auth.core.client.service.LocalAuthServiceImpl;
import com.fireman.yang.auth.core.client.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.client.session.processor.AccessTokenProcessor;
import com.fireman.yang.auth.core.client.session.processor.CookieTokenProcessor;
import com.fireman.yang.auth.core.client.supprot.DefaultSessionFactory;
import com.fireman.yang.auth.core.client.supprot.DefaultSessionTokenFactory;
import com.fireman.yang.auth.core.client.supprot.SingletonAuthClientManager;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.login.DefaultLoginTokenFactory;
import com.fireman.yang.auth.core.login.LoginTokenFactory;
import com.fireman.yang.auth.core.login.LoginTokenProcessor;
import com.fireman.yang.auth.core.login.PwdTokenProcessor;
import com.fireman.yang.auth.core.service.AuthService;
import com.fireman.yang.auth.session.SessionFactory;
import com.fireman.yang.auth.session.SessionTokenFactory;
import com.fireman.yang.auth.client.boot.annotation.SingleModelConditional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/4
 * @Description:
 */
@EnableConfigurationProperties(AuthClientConfigruationProperties.class)
@ConditionalOnProperty(value = "enable",prefix = "yang.auth.client", havingValue = "true")
@Configuration
@Import(AuthClientConfiguration.class)
public class AuthClientBootConfigruation {

    private static final  Logger log = LoggerFactory.getLogger(AuthClientBootConfigruation.class);


    @Bean
    @ConditionalOnMissingBean(AuthService.class)
    public AuthService authService(AuthClientConfigruationProperties authProperties){
        return new LocalAuthServiceImpl(authProperties.getUserInfoPath());
    }


    @Bean
    @ConditionalOnMissingBean(AccessTokenProcessor.class)
    public AccessTokenProcessor accessTokenProcessor(ClientSessionDao sessionDao){
        return new AccessTokenProcessor(sessionDao);
    }

    @Bean
    @ConditionalOnMissingBean(CookieTokenProcessor.class)
    public CookieTokenProcessor cookieTokenProcessor(ClientSessionDao sessionDao){
        return new CookieTokenProcessor(sessionDao);
    }

    @Bean
    @ConditionalOnMissingBean(LoginTokenFactory.class)
    public LoginTokenFactory loginTokenFactory(){
        return new DefaultLoginTokenFactory();
    }

    @Bean
    @ConditionalOnMissingBean(PwdTokenProcessor.class)
    public PwdTokenProcessor pwdTokenProcessor(AuthService authService){
        return new PwdTokenProcessor(authService);
    }

    @Bean
    @ConditionalOnMissingBean(DefaultSessionFactory.class)
    public DefaultSessionFactory sessionFactory(){
        return new DefaultSessionFactory();
    }

    @Bean
    @ConditionalOnMissingBean(DefaultSessionTokenFactory.class)
    public DefaultSessionTokenFactory sessionTokenFactory(AuthClientConfigruationProperties authProperties){
        return new DefaultSessionTokenFactory(authProperties.getClientId());
    }


    @Bean
    @ConditionalOnMissingBean(SingletonClientSessionDao.class)
    @Conditional(SingleModelConditional.class)
    public SingletonClientSessionDao singletonClientSessionDao(AuthClientConfigruationProperties authProperties){
        return new DefaultLocalSessionDao(authProperties.getClientId(), authProperties.getSessionExpire());
    }

    @Bean
    public AuthClientConfig authClientConfig(AuthClientConfigruationProperties authProperties,
                                             ClientSessionDao sessionDao,
                                             List<SessionTokenProcessor> sessionTokenProcessors,
                                             List<LoginTokenProcessor> loginTokenProcessors,
                                             SessionFactory sessionFactory,
                                             LoginTokenFactory loginTokenFactory,
                                             SessionTokenFactory sessionTokenFactory
    ){
       return new AuthClientConfig(authProperties.getFilters(), authProperties.getMapping(),
               sessionDao, LoginScop.toEnum(authProperties.getScop()),
               authProperties.getSessionExpire(), authProperties.getClientId(),
               sessionTokenProcessors, loginTokenProcessors, sessionFactory,
               sessionTokenFactory, loginTokenFactory, authProperties.getLoginUrl());
    }

    @Bean
    @ConditionalOnMissingBean(AuthClientManager.class)
    public AuthClientManager authClientManager(AuthClientConfig config){
        return new SingletonAuthClientManager(config);
    }



}
