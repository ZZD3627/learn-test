package com.example.controller;

import com.example.annotation.ValidEmail;
import com.example.annotation.ValidPhoneNumber;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PhoneController
 * @Description 测试验证器
 * @Author zhang zhengdong
 * @DATE 2024/12/27 21:00
 * @Version 1.0
 */
@RestController
@Validated
public class PhoneController {

	@GetMapping("/validatePhoneNumber")
	public String validatePhoneNumber(@ValidPhoneNumber String phoneNumber) {
		System.out.println(String.format("PhoneNumber: %s", phoneNumber));
		// 处理逻辑
		return "手机号验证通过";
	}

	@GetMapping("/email")
	public Object getEmail(@RequestParam @ValidEmail String email) {
		System.out.println(String.format("Email: %s", email));
		return email;
	}
}
