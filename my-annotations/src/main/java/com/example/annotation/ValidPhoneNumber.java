package com.example.annotation;


import com.example.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解：验证手机号
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)

public @interface ValidPhoneNumber {
	String message() default "无效的手机号格式";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
