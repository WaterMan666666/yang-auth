package com.fireman.yang.auth.client.config;

import com.fireman.yang.auth.core.client.config.AuthClientConfig;
import com.fireman.yang.auth.core.client.eunms.AuthFilterEnum;
import com.fireman.yang.auth.core.client.filter.AuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tongdong
 * @Date: 2020/7/1
 * @Description:
 */
@Configuration
public class AuthClientConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AuthClientConfiguration.class);

    @Bean
    public Filter authFilter(AuthClientConfig clientConfig, DefaultListableBeanFactory listableBeanFactory){
        //组织Filters
        List<String> filters = clientConfig.getFilters();
        Map<String, Filter> filterMap = new HashMap<>();
        for (int i = 0; i < filters.size(); i++) {
            String filterName = filters.get(i);
            Filter filter = null;
            try {
                filter = listableBeanFactory.getBean(filterName, Filter.class);
            }catch (NoSuchBeanDefinitionException e){
                AuthFilterEnum authFilterEnum = AuthFilterEnum.toEnum(filterName);
                if(authFilterEnum == null){
                    log.error("Filter({}) have never been created,please check the configuration.");
                    throw new RuntimeException("Filter(" + filterName + ") is not exist");
                }
                BeanDefinition bd = new RootBeanDefinition(authFilterEnum.filterClass);
                bd.setScope(BeanDefinition.SCOPE_SINGLETON);
                listableBeanFactory.registerBeanDefinition(filterName, bd);
                filter = listableBeanFactory.getBean(filterName, Filter.class);
            }
            filterMap.put(filterName, filter);
        }
        return new AuthFilter(clientConfig, filterMap);
    }



}
