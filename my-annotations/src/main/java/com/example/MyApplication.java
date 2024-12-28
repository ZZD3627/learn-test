package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ClassName MyApplication
 * @Description Spring启动类
 * @Author zhang zhengdong
 * @DATE 2024/12/27 15:34
 * @Version 1.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
public class MyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

}
