package com.example.processor;

import com.example.annotation.AutoSetValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


/**
 * @ClassName AutoSetValueProcessor
 * @Description 自定义处理器：负责扫描并赋值注解字段
 * @Author zhang zhengdong
 * @DATE 2024/12/27 15:20
 * @Version 1.0
 */
@Component
public class AutoSetValueProcessor {

	@Autowired
	private ApplicationContext context;

	public void process(Object target) throws IllegalAccessException {

		//获取所有的字段
		Field[] fields = target.getClass().getDeclaredFields();

		//遍历所有字段，检查是否标注了 @AutoSetValue 注解

		for (Field field : fields) {
			if (field.isAnnotationPresent(AutoSetValue.class)) {
				//获取注解值
				AutoSetValue autoSetValue = field.getAnnotation(AutoSetValue.class);
				String value = autoSetValue.value();

				//由于自动可能是private，需要设置accessible
				field.setAccessible(true);

				//进行赋值
				field.set(target, value);
			}
		}
	}

}
