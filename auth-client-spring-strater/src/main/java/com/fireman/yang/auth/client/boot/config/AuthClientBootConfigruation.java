package com.fireman.yang.auth.client.boot.config;

import com.fireman.yang.auth.client.config.AuthClientConfiguration;
import com.fireman.yang.auth.core.client.*;
import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.dao.DefaultLocalSessionDao;
import com.fireman.yang.auth.core.client.filter.AuthenticationFilter;
import com.fireman.yang.auth.core.client.filter.SsoAuthenticationFilter;
import com.fireman.yang.auth.core.client.handler.LoginSuccessHandler;
import com.fireman.yang.auth.core.client.handler.UnAuthenticateHandler;
import com.fireman.yang.auth.core.client.handler.support.DefaultLoginSuccessHandler;
import com.fireman.yang.auth.core.client.handler.support.DefaultUnAuthenticateHandler;
import com.fireman.yang.auth.core.client.service.LocalAuthServiceImpl;
import com.fireman.yang.auth.core.client.session.ClientSessionDao;
import com.fireman.yang.auth.core.client.session.processor.AccessTokenProcessor;
import com.fireman.yang.auth.core.client.session.processor.CookieTokenProcessor;
import com.fireman.yang.auth.core.client.sso.DefaultClientSsoService;
import com.fireman.yang.auth.core.session.SessionDao;
import com.fireman.yang.auth.core.session.support.DefaultSessionFactory;
import com.fireman.yang.auth.core.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.session.support.DefaultSessionTokenFactory;
import com.fireman.yang.auth.core.client.supprot.SingletonAuthClientManager;
import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.login.*;
import com.fireman.yang.auth.core.service.AuthService;
import com.fireman.yang.auth.core.sso.ClientSsoService;
import com.fireman.yang.auth.core.session.SessionFactory;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.client.boot.annotation.SingleModelConditional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
        return new LocalAuthServiceImpl(authProperties.getInfoBasePath());
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
               authProperties.getSessionExpire(), authProperties.getClientId(),authProperties.getClientSecret(),
               sessionTokenProcessors, loginTokenProcessors, sessionFactory,
               sessionTokenFactory, loginTokenFactory, authProperties.getLoginUri(),
               authProperties.authTokenUri, authProperties.authDomain);
    }

    @Bean
    @ConditionalOnMissingBean(AuthClientManager.class)
    public AuthClientManager authClientManager(AuthClientConfig config){
        return new SingletonAuthClientManager(config);
    }


    @Bean
    @ConditionalOnMissingBean(UnAuthenticateHandler.class)
    @ConditionalOnBean(SsoAuthenticationFilter.class)
    public UnAuthenticateHandler unAuthenticateHandler(AuthClientConfig config){
        return new DefaultUnAuthenticateHandler(config.getClientId(), config.getLoginUri());
    }

    @Bean
    @ConditionalOnMissingBean(UnAuthenticateHandler.class)
    @ConditionalOnBean(SsoAuthenticationFilter.class)
    public LoginSuccessHandler loginSuccessHandler(){
        return new DefaultLoginSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean(AuthorizeCodeTokenProcessor.class)
    @ConditionalOnBean(SsoAuthenticationFilter.class)
    public AuthorizeCodeTokenProcessor authorizeCodeTokenProcessor(ClientSsoService clientSsoService){
        return new AuthorizeCodeTokenProcessor(clientSsoService);
    }

    @Bean
    @ConditionalOnMissingBean(ClientSsoService.class)
    @ConditionalOnBean(SsoAuthenticationFilter.class)
    public ClientSsoService clientSsoService(AuthClientConfig config){
        return new DefaultClientSsoService(config);
    }

    @Bean(name = "auth")
    @ConditionalOnProperty(value = "enable",prefix = "yang.auth.client.model", havingValue = "sso")
    @ConditionalOnMissingBean(AuthenticationFilter.class)
    @ConditionalOnBean(AuthClientManager.class)
    public SsoAuthenticationFilter ssoAuthenticationFilter(AuthClientConfig config, AuthClientManager authClientManager, UnAuthenticateHandler handler){
        return new SsoAuthenticationFilter(config, authClientManager, handler);
    }


}
