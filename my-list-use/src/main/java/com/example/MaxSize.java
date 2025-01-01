package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MaxSize
 * @Description list最大长度测试
 * @Author zhang zhengdong
 * @DATE 2024/12/29 16:03
 * @Version 1.0
 */
public class MaxSize {
	public static void main(String[] args) {
		//在创建对象的时候直接报了 java.lang.OutOfMemoryError
		Integer[] integers = new Integer[Integer.MAX_VALUE - 100];
		System.out.println(integers.length);
		//Arraylist最大的长度为Integer.MAX_VALUE-8
		List<String> maxSizeList = new ArrayList<>(Integer.MAX_VALUE);
		System.out.println(maxSizeList.size());
	}
}
