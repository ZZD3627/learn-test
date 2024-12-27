package com.example;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchTest
 * @Description 通过CountDownLatch实现多线程，打印苹果
 * @Author zhang zhengdong
 * @DATE 2024/12/27 11:35
 * @Version 1.0
 */
public class CountDownLatchTest {

	public static void main(String[] args) throws InterruptedException {
		int numThreads = 5;
		CountDownLatch latch = new CountDownLatch(numThreads);

		for (int i = 0; i < numThreads; i++) {
			new Thread(new CountDownLatchWorker(latch)).start();
		}
		latch.await();
		System.out.println("所有线程都已经打印苹果");
	}
}

class CountDownLatchWorker implements Runnable {
	private final CountDownLatch latch;

	public CountDownLatchWorker(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println(String.format("%s 苹果", Thread.currentThread().getName()));
		latch.countDown(); // 每个线程完成时减1
	}
}
