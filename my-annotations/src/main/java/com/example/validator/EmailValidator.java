package com.example.validator;

import com.example.annotation.ValidEmail;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


/**
 * @ClassName EmailValidator
 * @Description email验证器
 * @Author zhang zhengdong
 * @DATE 2024/12/27 19:13
 * @Version 1.0
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

	private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isEmpty()) {
			return false; // 空值和空字符串被视为无效
		}
		return pattern.matcher(value).matches();
	}
}
