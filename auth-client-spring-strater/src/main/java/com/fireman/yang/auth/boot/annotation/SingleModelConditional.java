package com.fireman.yang.auth.boot.annotation;

import com.fireman.yang.auth.core.client.eunms.AuthModel;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author tongdong
 * @Date: 2020/11/6
 * @Description:
 */
public class SingleModelConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("yang.auth.client.model");
        if(property == null) {
            return true;
        }
        AuthModel authModel = AuthModel.toEnum(property);
        return authModel == AuthModel.single || authModel == null;
    }
}
