package com.example.entity;

import com.example.annotation.AutoSetValue;
import com.example.processor.AutoSetValueProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName MyObject
 * @Description 实体类
 * @Author zhang zhengdong
 * @DATE 2024/12/27 15:28
 * @Version 1.0
 */
@Component
public class MyObject {

	@AutoSetValue(value = "Hello World")
	private String message;

	@AutoSetValue("42")
	private String number;

	@Autowired
	private AutoSetValueProcessor processor;

	@PostConstruct
	public void init() throws IllegalAccessException {
		processor.process(this);
	}

	public String display() {
		System.out.println(String.format("Message ：%s", message));
		System.out.println(String.format("Number ：%s", number));
		return String.format("Message ： %s -- Number ：%s ", message, number);
	}

}
