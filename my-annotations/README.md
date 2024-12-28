# My annotations

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

##### @AutoSetValue

    @AutoSetValue注解拥有在对象的字段上设置特定的值。其实现涉及反射机制，在对象实例化或初始化时，
    扫描其字段，检测哪些字段被标注了@AutoSetValue，然后根据预定义的规则或配置为这些字段赋值。

##### @LogExecutionTime @MyCacheable

    @LogExecutionTime 用于记录方法的执行时间。
    @LogExecutionTime 通过AOP（面向切面编程）技术，在方法执行的前后插入代码，通过反射执行代码（能够使用AOP的方法是通过动态代理的方式创建
    的对象，默认使用jdk动态代理，也可配置使用cglib代理）

    @MyCacheable 自定义缓存数据
    @MyCacheable 通过AOP技术，在方法执行前检测缓存中是否存在结果，若存在则直接返回；否则执行方法将结果存入缓存，通过动态代理创建对象，通过
    反射执行

##### @ValidEmail @ValidPhoneNumber

    @ValidEmail和@ValidPhoneNumber基于Java的Bean Validation规范
    @ValidEmail和@ValidPhoneNumber注解与相应的验证器ConstraintValidator配合使用，以定义具体验证逻辑
    @ValidEmail和@ValidPhoneNumber通过反射机制，获取被验证对象的属性和注解信息，通过反射验证器动态地访问对象的字段/方法和注解，从而执行
    相应的验证逻辑


