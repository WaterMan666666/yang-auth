package com.fireman.yang.auth.core.server.utils.validate;

import com.fireman.yang.auth.core.common.constants.AuthConstants;
import com.fireman.yang.auth.core.exception.ParameterErrorException;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @ClassName ValidateUtils
 * @Author TD
 * @Date 2019/1/15 17:15
 * @Description 校验工具
 */
public class ValidateUtils {

    public static Validator validator;

    static {

        validator = Validation
                .byProvider(HibernateValidator.class)
                .configure()
                //快速返回模式，有一个验证失败立即返回错误信息
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

    /**
     * 静态方法校验使用的
     *
     * @param object
     * @return
     */
    public static String validate(Object object) {
        if(object == null){
            throw new ParameterErrorException("参数不完整");
        }
        Set<ConstraintViolation<Object>> validate = validator.validate(object);
        return resultValidate(validate);

    }

    /**
     * 静态方法校验使用，并且带分组的
     *
     * @param object
     * @param group
     * @return
     */
    public static String validate(Object object, Class group) {
        Set<ConstraintViolation<Object>> validate = validator.validate(object, group);
        return resultValidate(validate);
    }


    private static String resultValidate(Set<ConstraintViolation<Object>> validate) {
        if (!validate.isEmpty()) {
            final StringBuffer stringBuffer = new StringBuffer();
            validate.stream().forEach(
                    item -> stringBuffer.append(item.getMessage()).append(","));
            stringBuffer.setLength(stringBuffer.length() - 1);
            return stringBuffer.toString();
        }
        return AuthConstants.COMMON_SUCCESS;
    }

}
