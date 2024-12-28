package com.example.validator;

import com.example.annotation.ValidPhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @ClassName PhoneNumberValidator
 * @Description 手机号验证器
 * @Author zhang zhengdong
 * @DATE 2024/12/27 20:58
 * @Version 1.0
 */
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

	// 定义手机号的正则表达式，根据需要进行调整
	private static final String PHONE_NUMBER_REGEX = "^1[3-9]\\d{9}$";
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);

	@Override
	public void initialize(ValidPhoneNumber constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		if (phoneNumber == null) {
			return false;
		}
		return PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
	}
}

