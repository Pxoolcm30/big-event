package com.pxoolcm.anno;

import com.pxoolcm.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/10 14:19
 */
@Documented
@Constraint(validatedBy = {StateValidation.class})//指定提供校验规则的类
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    String message() default "state提供的参数不合理";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
