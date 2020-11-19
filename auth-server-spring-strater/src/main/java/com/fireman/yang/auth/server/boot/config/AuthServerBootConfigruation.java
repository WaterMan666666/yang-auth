package com.fireman.yang.auth.server.boot.config;

import com.fireman.yang.auth.core.common.enums.LoginScop;
import com.fireman.yang.auth.core.login.DefaultLoginTokenFactory;
import com.fireman.yang.auth.core.login.LoginTokenFactory;
import com.fireman.yang.auth.core.login.LoginTokenProcessor;
import com.fireman.yang.auth.core.login.PwdTokenProcessor;
import com.fireman.yang.auth.core.server.AuthSsoServerManager;
import com.fireman.yang.auth.core.server.support.ServerSessionDao;
import com.fireman.yang.auth.core.server.config.AuthServerConfig;
import com.fireman.yang.auth.core.server.service.AuthServerCoreService;
import com.fireman.yang.auth.core.server.service.AuthServerService;
import com.fireman.yang.auth.core.server.support.*;
import com.fireman.yang.auth.core.server.web.filter.AuthServerFilter;
import com.fireman.yang.auth.core.service.AuthService;
import com.fireman.yang.auth.core.session.SessionDao;
import com.fireman.yang.auth.core.session.SessionFactory;
import com.fireman.yang.auth.core.session.SessionTokenFactory;
import com.fireman.yang.auth.core.session.SessionTokenProcessor;
import com.fireman.yang.auth.core.session.support.DefaultSessionFactory;
import com.fireman.yang.auth.core.session.support.DefaultSessionTokenFactory;
import com.fireman.yang.auth.core.session.support.processor.AccessTokenProcessor;
import com.fireman.yang.auth.core.session.support.processor.CookieTokenProcessor;
import com.fireman.yang.auth.server.config.AuthServerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * @author tongdong
 * @Date: 2020/11/4
 * @Description:
 */
@EnableConfigurationProperties(AuthServerConfigruationProperties.class)
@ConditionalOnProperty(value = "enable",prefix = "yang.auth.server", havingValue = "true")
@Configuration
@Import(AuthServerConfiguration.class)
public class AuthServerBootConfigruation {

    private static final  Logger log = LoggerFactory.getLogger(AuthServerBootConfigruation.class);


    @Bean
    @ConditionalOnMissingBean(AuthServerService.class)
    public AuthServerService authService(AuthServerConfigruationProperties authProperties){
        return new LocalAuthServerServiceImpl(authProperties.getInfoBasePath());
    }

    @Bean("auth-server")
    public AuthServerFilter authServerFilter(AuthSsoServerManager ssoServerManager, SessionTokenFactory sessionTokenFactory,
                                             AuthServerConfigruationProperties properties){
        return new AuthServerFilter(ssoServerManager, sessionTokenFactory, properties.getLoginUri(), properties.getAuthorizeUri(), properties.getTokenUri() );
    }

    @Bean
    public AuthSsoServerManager clientManager(AuthServerConfig config){
        return new DefaultAuthSsoServerManager(config.getScop(), config.getSessionTokenProcessors(),
                config.getLoginTokenProcessors(), config.getSessionFactory(), config.getSessionTokenFactory(),
                config.getSessionDao());
    }

    @Bean
    @ConditionalOnMissingBean(DefaultSessionTokenFactory.class)
    public DefaultSessionTokenFactory sessionTokenFactory(){
        return new DefaultSessionTokenFactory("authServer");
    }


    @Bean
    @ConditionalOnMissingBean(LoginTokenFactory.class)
    public LoginTokenFactory loginTokenFactory(){
        return new DefaultLoginTokenFactory();
    }

    @Bean
    @ConditionalOnMissingBean(AuthServerCoreService.class)
    public AuthServerCoreService authServerCoreService(List<ResponseTypeProcessor> responseTypeProcessors,
                                                       List<GrantTypeProcessor> grantTypeProcessors,
                                                       AuthServerService authServiceService){
        return new AuthServerCoreServiceSupport(responseTypeProcessors, grantTypeProcessors, authServiceService);
    }


    @Bean
    public AccessTokenProcessor accessTokenProcessor(SessionDao sessionDao){
        return new AccessTokenProcessor(sessionDao);
    }

    @Bean
    public CookieTokenProcessor cookieTokenProcessor(SessionDao sessionDao){
        return new CookieTokenProcessor(sessionDao);
    }

    @Bean
    public PwdTokenProcessor pwdTokenProcessor(AuthService authService){
        return new PwdTokenProcessor(authService);
    }

    @Bean
    public SessionFactory sessionFactory(){
        return new DefaultSessionFactory();
    }

    @Bean
    public ServerSessionDao sessionDao(AuthServerConfigruationProperties authProperties){
        return new DefaultLocalServerSessionDao(authProperties.getSessionExpire());
    }

    @Bean
    public AuthServerConfig authClientConfig(AuthServerConfigruationProperties authProperties,
                                             List<SessionTokenProcessor> sessionTokenProcessors,
                                             List<LoginTokenProcessor> loginTokenProcessors,
                                             SessionFactory sessionFactory,
                                             SessionTokenFactory sessionTokenFactory,
                                             ServerSessionDao sessionDao){
        return new AuthServerConfig(LoginScop.toEnum(authProperties.getScop()),
                sessionTokenProcessors, loginTokenProcessors, sessionFactory, sessionTokenFactory,
                sessionDao, authProperties.getInfoBasePath(), authProperties.getAuthorizeUri(), authProperties.getTokenUri(), authProperties.getLoginUri());
    }
}