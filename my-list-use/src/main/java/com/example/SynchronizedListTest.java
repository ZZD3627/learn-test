package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName SynchronizedListTest
 * @Description SynchronizedList测试
 * @Author zhang zhengdong
 * @DATE 2024/12/31 16:39
 * @Version 1.0
 */
public class SynchronizedListTest {

	/**
	 * 调用Collections..synchronizedList(list)之后返回的list为什么是线程安全的，因为其返回的对象是Collections类中定义的一个静态内部类
	 * SynchronizedList,该类实现了List接口，并持有一个内部的List对象引用
	 *
	 * SynchronizedList在执行列表操作时，使用一个互斥锁（mutex）对关键代码块进行同步。默认情况下，mutex是SynchronizedList对象自身
	 *
	 * 在SynchronizedList中，所有修改列表状态的方法（如 add/remove/set等）以及可能导致数据不一致的读取方法（如 get/size等）都是在同步代码
	 * 块中执行，以确保线程安全
	 *
	 * Collections类中synchronizedList方法的实现代码：
	 * public static <T> List<T> synchronizedList(List<T> list) {
	 *     return (list instanceof RandomAccess ?
	 *             new SynchronizedRandomAccessList<>(list) :
	 *             new SynchronizedList<>(list));
	 * }
	 *
	 * 该方法根据传入的列表是否实现了RandomAccess接口，返回相应的同步列表实现。对于实现了RandomAccess接口的列表（如 Arraylist）,
	 * 返回SynchronizedRandomAccessList对象；否则，返回SynchronizedList对象
	 * SynchronizedList对象部分实现代码：
	 *static class SynchronizedList<E> extends SynchronizedCollection<E> implements List<E> {
	 *     final List<E> list;
	 *
	 *     SynchronizedList(List<E> list) {
	 *         super(list);
	 *         this.list = list;
	 *     }
	 *
	 *     public E get(int index) {
	 *         synchronized (mutex) {
	 *             return list.get(index);
	 *         }
	 *     }
	 *
	 *     public E set(int index, E element) {
	 *         synchronized (mutex) {
	 *             return list.set(index, element);
	 *         }
	 *     }
	 *
	 *     public void add(int index, E element) {
	 *         synchronized (mutex) {
	 *             list.add(index, element);
	 *         }
	 *     }
	 *
	 *     public E remove(int index) {
	 *         synchronized (mutex) {
	 *             return list.remove(index);
	 *         }
	 *     }
	 *
	 *     // 其他方法省略
	 * }
	 * SynchronizedRandomAccessList方法中的代码与SynchronizedList类似都是在执行代码的适合加入了synchronized(mutex)，
	 * 需要注意的是，虽然synchronzedList提供了线程安全的列表，但是在使用迭代器遍历的适合也需要进行外部的同步
	 *
	 */

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();

		List<Integer> synchronizedList = Collections.synchronizedList(list);
		// 创建并启动多个线程进行增删改查操作
		Thread t1 = new Thread(() -> addElements(synchronizedList, 0, 5));
		Thread t2 = new Thread(() -> addElements(synchronizedList, 5, 10));
		Thread t3 = new Thread(() -> removeElement(synchronizedList, 3));
		Thread t4 = new Thread(() -> updateElement(synchronizedList, 2, 20));
		Thread t5 = new Thread(() -> printElements(synchronizedList));

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
	private static void addElements(List<Integer> list, int start, int end) {
		for (int i = start; i < end; i++) {
			list.add(i);
			System.out.println("Added: " + i);
		}
	}

	// 删除元素
	private static void removeElement(List<Integer> list, int value) {
		if (list.remove((Integer) value)) {
			System.out.println("Removed: " + value);
		} else {
			System.out.println("Value not found: " + value);
		}
	}

	// 更新元素
	private static void updateElement(List<Integer> list, int index, int newValue) {
		synchronized (list) {
			if (index >= 0 && index < list.size()) {
				int oldValue = list.get(index);
				list.set(index, newValue);
				System.out.println("Updated index " + index + " from " + oldValue + " to " + newValue);
			} else {
				System.out.println("Index out of bounds: " + index);
			}
		}
	}

	// 打印所有元素
	private static void printElements(List<Integer> list) {
		synchronized (list) {
			System.out.println("Current List: " + list);
		}
	}
}
