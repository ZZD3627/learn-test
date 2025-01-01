package com.example;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName CopyOnWriteArrayListTest
 * @Description CopyOnWriteArrayList测试
 * @Author zhang zhengdong
 * @DATE 2024/12/31 15:40
 * @Version 1.0
 */
public class CopyOnWriteArrayListTest {


	/**
	 * 使用CopyOnWriteArrayList实现增删改查，多线程执行时，多线程时无法确定那个线程先执行
	 * 	但是可以使用join方法实现在主线程时这些线程是执行完成的，然后再去执行主线程
	 *
	 * 	Thread类的join方法用于让当前线程等待另一个线程的完成。具体而言，当在主线程中调用某个线程对象的join方法时，主线程将会被阻塞，
	 * 	直到该线程执行完毕，主线程才会继续执行后续的代码。这对于需要确保某些线程在主线程继续执行之前完成其任务的场景非常有用
	 *
	 *  CopyOnWriteArrayList线程安全解读：
	 *    写时复制机制：
	 *    	当对列表执行修改操作（如:add set或remove）时，不直接修改原数组，而是先复制原数组到一个新数组，再在新数组上进行修改，
	 *    	最终将新数组设置为当前的内部数组
	 *    这种机制保证了任何正在读取的线程都可以安全地使用旧数组，避免了读写冲突
	 *    独立的锁控制：
	 *    	修改操作（写操作）被独占锁ReentrantLock保护，确保在写操作过程中不会有其他线程干扰
	 *    	读操作直接使用当前数组，不需要锁，从而提高读性能
	 *   不可变快照：
	 *   	迭代器返回当前数组的快照，因此在遍历期间对列表的修改不会影响迭代器，也不会抛出ConcurrentModificationException
	 *   线程安全的关键代码片段：
	 *   The lock protecting all mutators
	 *   final transient ReentrantLock lock = new ReentrantLock();
	 *
	 *   添加元素，获取锁后，复制当前数组，进行修改，再替换原数组
	 *   public boolean add(E e) {
	 *     final ReentrantLock lock = this.lock;
	 *     lock.lock(); // 获取锁
	 *     try {
	 *         Object[] elements = getArray(); // 获取当前数组
	 *         int len = elements.length;
	 *         Object[] newElements = Arrays.copyOf(elements, len + 1); // 复制数组
	 *         newElements[len] = e; // 在新数组中添加元素
	 *         setArray(newElements); // 将新数组设置为当前数组
	 *         return true;
	 *     } finally {
	 *         lock.unlock(); // 释放锁
	 *     }
	 * }
	 *  修改元素，获取锁，先复制当前数组创建副本，之后再进行替换
	 *  public E set(int index, E element) {
	 *     final ReentrantLock lock = this.lock;
	 *     lock.lock(); // 获取锁
	 *     try {
	 *         Object[] elements = getArray(); // 获取当前数组
	 *         E oldValue = get(elements, index); // 获取旧值
	 *         int len = elements.length;
	 *         Object[] newElements = Arrays.copyOf(elements, len); // 复制数组
	 *         newElements[index] = element; // 在新数组中修改元素
	 *         setArray(newElements); // 替换数组
	 *         return oldValue;
	 *     } finally {
	 *         lock.unlock(); // 释放锁
	 *     }
	 * }
	 *   删除元素 删除操作也是先复制，复制后再进行删除操作
	 *   public E remove(int index) {
	 *     final ReentrantLock lock = this.lock;
	 *     lock.lock(); // 获取锁
	 *     try {
	 *         Object[] elements = getArray(); // 获取当前数组
	 *         int len = elements.length;
	 *         E oldValue = get(elements, index); // 获取旧值
	 *         Object[] newElements = new Object[len - 1]; // 创建新数组
	 *         System.arraycopy(elements, 0, newElements, 0, index); // 复制前半部分
	 *         System.arraycopy(elements, index + 1, newElements, index, len - index - 1); // 复制后半部分
	 *         setArray(newElements); // 替换数组
	 *         return oldValue;
	 *     } finally {
	 *         lock.unlock(); // 释放锁
	 *     }
	 * }
	 *
	 *   读取操作 读取操作无需加锁，直接返回当前数组的值，确保高效
	 *   public E get(int index) {
	 *     return get(getArray(), index); // 直接读取当前数组，不加锁
	 * }
	 *
	 *  迭代器的线程安全  迭代器使用数组的快照，避免并发修改异常。
	 *  public Iterator<E> iterator() {
	 *     return new COWIterator<E>(getArray(), 0);
	 * }
	 *
	 * static final class COWIterator<E> implements ListIterator<E> {
	 *     private final Object[] snapshot; // 快照
	 *     private int cursor;
	 *
	 *     private COWIterator(Object[] elements, int initialCursor) {
	 *         cursor = initialCursor;
	 *         snapshot = elements; // 迭代器使用当前数组的快照
	 *     }
	 *
	 *     public boolean hasNext() {
	 *         return cursor < snapshot.length;
	 *     }
	 *
	 *     public E next() {
	 *         if (!hasNext())
	 *             throw new NoSuchElementException();
	 *         return (E) snapshot[cursor++];
	 *     }
	 * }
	 *
	 * 特点：
	 * 	写时复制：每次写操作都会创建数组副本，避免读写冲突
	 * 	锁机制：所有写操作都使用ReentrantLock进行同步，确保线程互斥
	 * 	不可变快照：都操作使用数组的快照，保证迭代期间数据一致性
	 *
	 * 	缺点：
	 * 	 写操作开销大：每次写操作都需要复制数组，适合读多写少的场景
	 * 	 内存占用高：频繁的写操作可能导致大量临时数组占用内存
	 */
	private static CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> addElements(0, 5));
		Thread t2 = new Thread(() -> addElements(5, 10));
		Thread t3 = new Thread(() -> removeElement(3));
		Thread t4 = new Thread(() -> updateElement(2, 20));
		Thread t5 = new Thread(CopyOnWriteArrayListTest::printElements);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();

		printElements();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
		} catch (Exception e) {
			Thread.currentThread().interrupt();
		}
		printElements();

		System.out.println(String.format("%s 结束线程", Thread.currentThread().getName()));

	}

	private static void updateElement(int index, int newValue) {
		if (index >= 0 && index < list.size()) {
			Integer old = list.get(index);
			list.set(index, newValue);
			System.out.println("Updated index " + index + " from " + old + " to " + newValue);
		} else {
			System.out.println("Index out of bounds: " + index);
		}
	}

	private static void removeElement(int value) {
		if (list.remove(Integer.valueOf(value))) {
			System.out.println("Removed: " + value);
		} else {
			System.out.println("Value not found: " + value);
		}
	}

	private static void addElements(int start, int end) {
		for (int i = start; i < end; i++) {
			list.add(i);
			System.out.println("Added: " + i);
		}
	}

	// 打印所有元素
	private static void printElements() {
		System.out.println("Current List: " + list);
	}
}
