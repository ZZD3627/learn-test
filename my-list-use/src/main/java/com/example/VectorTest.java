package com.example;

import java.util.Vector;

/**
 * @ClassName VectorTest
 * @Description Vector类测试
 * @Author zhang zhengdong
 * @DATE 2024/12/31 16:36
 * @Version 1.0
 */
public class VectorTest {

	/**
	 * Vector类线程安全的原因是在所有可能存在线程不安全的地方都加入了synchronized关键字
	 */

	
	public static void main(String[] args) {
		Vector<Integer> vector = new Vector<>();

		// 创建并启动多个线程进行增删改查操作
		Thread t1 = new Thread(() -> addElements(vector, 0, 5));
		Thread t2 = new Thread(() -> addElements(vector, 5, 10));
		Thread t3 = new Thread(() -> removeElement(vector, 3));
		Thread t4 = new Thread(() -> updateElement(vector, 2, 20));
		Thread t5 = new Thread(() -> printElements(vector));

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		// 等待所有线程执行完毕
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// 添加元素
	private static void addElements(Vector<Integer> vector, int start, int end) {
		for (int i = start; i < end; i++) {
			vector.add(i);
			System.out.println("Added: " + i);
		}
	}

	// 删除元素
	private static void removeElement(Vector<Integer> vector, int value) {
		if (vector.remove((Integer) value)) {
			System.out.println("Removed: " + value);
		} else {
			System.out.println("Value not found: " + value);
		}
	}

	// 更新元素
	private static void updateElement(Vector<Integer> vector, int index, int newValue) {
		if (index >= 0 && index < vector.size()) {
			int oldValue = vector.get(index);
			vector.set(index, newValue);
			System.out.println("Updated index " + index + " from " + oldValue + " to " + newValue);
		} else {
			System.out.println("Index out of bounds: " + index);
		}
	}

	// 打印所有元素
	private static void printElements(Vector<Integer> vector) {
		System.out.println("Current Vector: " + vector);
	}
}
