# learning-records
multi-threaded-communication 多线程之间的通讯
    - CountDownLatchTest.java 使用 CountDownLatch实现多线程之间的通讯，使用.await()方法等待所有线程完成
    - CyclicBarrierTest.java 使用 CyclicBarrier实现多线程之间的通讯，使用.await()方法等待所有线程完成
    - ExecutorServiceTest.java 使用ExecutorService实现多线程之间的通讯，如果线程中返回信息则直接使用这种方式，这种方式能够从子线程中返回对象，通过.get()方法等待其他线程
    - ThreadTest.java 使用Thread实现多线之间的通讯，使用join()方法实现线程阻塞并进行通讯
