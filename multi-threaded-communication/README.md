# Multi-threaded communication
任务实现在多个线程中分别打印“苹果”，在所有的苹果打印完成后在主线程中打印所有线程都已经“完成打印”
#### 实现方式1
使用CountDownLatch，用于让一个或多个线程等待直到其他线程执行完成。CountDownLatch允许设置一个计数器，其他线程会阻塞，直到计数器减到0，主线程通过await()等待计数器减到0。
* 每次countDown()调用时，计数器会减1.调用await()的线程会等待直到计数器为0.
#### 实现方式2
使用CyclicBarrier，CyclicBarrier是一种同步工具，它运行多个线程相互等待，直到所有线程都达到某个共同的屏障点。CyclicBarrier会重置设置，可以用于循环多次的场景。
CyclicBarrier会自动执行await()同步方法。所有线程都执行完毕后，主线程会继续执行。
#### 实现方式3
使用ExecutorService，ExecutorService是一个线程池，它用于管理和控制线程的生命周期。可以通过submit()方法提交任务，返回一个Future对象，Future可以用来检查线程的执行状态。
所有线程在池中都执行，主线程等待每一个线程的Future对象的get()方法返回，直到所有线程完成
#### 实现方式4
Thread.join()是一种简单的线程协调方式。主线程调用join()方法，等待其他线程执行完成后再继续执行。join()方法会阻塞主线程，直到调用join()的线程执行完毕。

其中Thread.join()是上面方法中最简单的一种，其是通过继承Thread类，直接创建线程执行，ExecutorService是比较传统的方式，如果需要从线程中获取到什么信息可以使用这个方式。
使用CountDownLatch和CyclicBarrier是比较推荐的方式，CountDownLatch和CyclicBarrier都不需要在执行完毕后手动关闭线程。



