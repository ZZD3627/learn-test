# learning-records
##### multi-threaded-communication 多线程之间的通讯

    - CountDownLatchTest.java 使用 CountDownLatch实现多线程之间的通讯，使用.await()方法等待所有线程完成

    - CyclicBarrierTest.java 使用 CyclicBarrier实现多线程之间的通讯，使用.await()方法等待所有线程完成

    - ExecutorServiceTest.java 使用ExecutorService实现多线程之间的通讯，如果线程中返回信息则直接使用这种方式，这种方式能够从子线程中返回对象，通过.get()方法等待其他线程

    - ThreadTest.java 使用Thread实现多线之间的通讯，使用join()方法实现线程阻塞并进行通讯
##### My annotations
    参数自动赋值注解 
    注解 - AutoSetValue.java 
    处理器 - AutoSetValueProcessor.java
    验证接口 /hello
    
    记录方法执行时间注解
    注解 - LogExecutionTime.java
    记录方法执行时间，反向代理 - LoggingAspect.java
    验证接口 /perform
    
    自定义缓存注解
    注解 - MyCacheable.java
    缓存反向代理 - CacheAspect.java (此处一点要使用CacheManager保存缓存数据，否则可能在Spring中无法获取到缓存)
    验证接口 /cache (存储缓存)
            /cacheData (获取缓存数据)

    验证邮箱格式注解
    注解- ValidEmail.java
    验证器 EmailValidator.java
    验证接口 /email (需要传参)

    验证手机号注解
    注解 -ValidPhoneNumber.java
    验证器 -PhoneNumberValidator.java
    验证接口 /validatePhoneNumber (需要传参)
