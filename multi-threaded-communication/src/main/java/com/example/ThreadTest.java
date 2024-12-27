package com.example;

/**
 * @ClassName ThreadTest
 * @Description TODO
 * @Author zhang zhengdong
 * @DATE 2024/12/27 14:18
 * @Version 1.0
 */
public class ThreadTest {
	public static void main(String[] args) throws InterruptedException {
		int numThreads = 5;
		Thread[] threads = new Thread[numThreads];

		for (int i = 0; i < numThreads; i++) {
			threads[i] = new ThreadWorker();
			threads[i].start();
		}

		for (Thread thread : threads) {
			thread.join();
		}
		System.out.println("所有线程都已经打印苹果");
	}
}

class ThreadWorker extends Thread {
	@Override
	public void run() {
		System.out.println(String.format("%s 苹果", Thread.currentThread().getName()));
	}
}
