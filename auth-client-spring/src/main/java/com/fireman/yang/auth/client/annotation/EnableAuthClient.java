package com.fireman.yang.auth.client.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author tongdong
 * @Date: 2020/11/2
 * @Description:
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
//@Import({AuthClientConfigurationRegistrar.class})
public @interface EnableAuthClient {

    String[] scanBasePackages() default {};

    boolean multipleConfig() default true;
}
