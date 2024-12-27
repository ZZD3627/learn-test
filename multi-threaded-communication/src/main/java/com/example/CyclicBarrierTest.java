package com.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierTest
 * @Description 通过CyclicBarrier实现多线程打印苹果
 * @Author zhang zhengdong
 * @DATE 2024/12/27 13:59
 * @Version 1.0
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		int numThreads = 5;
		CyclicBarrier barrier = new CyclicBarrier(
				numThreads, () -> {
			System.out.println("所有线程都已经打印苹果");
		});

		for (int i = 0; i < numThreads; i++) {
			new Thread(new CyclicBarrierWorker(barrier)).start();
		}
	}

}


class CyclicBarrierWorker implements Runnable {

	private final CyclicBarrier cyclicBarrier;

	public CyclicBarrierWorker(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {

		System.out.println(String.format(("%s 苹果"), Thread.currentThread().getName()));
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}

	}
}