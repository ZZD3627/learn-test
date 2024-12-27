package com.example;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @ClassName ExecutorServiceTest
 * @Description 通过ExecutorService实现多线程之间的通讯
 * @Author zhang zhengdong
 * @DATE 2024/12/27 14:11
 * @Version 1.0
 */
public class ExecutorServiceTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		int numThreads = 5;
		ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
		ArrayList<Future<Void>> futureArrayList = new ArrayList<>();

		for (int i = 0; i < numThreads; i++) {
			futureArrayList.add(executorService.submit(new ExecutorServiceTask()));
		}

		for (Future<Void> voidFuture : futureArrayList) {
			voidFuture.get();
		}

		System.out.println("所有线程都已经打印苹果");
		executorService.shutdown();

	}
}

class ExecutorServiceTask implements Callable<Void> {

	@Override
	public Void call() throws Exception {
		System.out.println(String.format("%s 打印苹果", Thread.currentThread().getName()));
		return null;
	}
}

