package com.example.annotation;

import com.example.validator.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解：验证邮箱格式
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class) // 指定验证逻辑
public @interface ValidEmail {
	String message() default "Invalid email format";  // 默认错误信息

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}