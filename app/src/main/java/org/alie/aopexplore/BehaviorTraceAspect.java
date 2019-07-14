package org.alie.aopexplore;

import android.util.Log;

import org.alie.aopexplore.annotation.BehaviorTrace;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Alie on 2019/7/14.
 * 类描述 切面的业务操作类（面类）
 * 1.声明为切面 使用@Aspect
 * 2.定义切面规则
 */
@Aspect
public class BehaviorTraceAspect {
    // 1.定义切面规则
    // 1.1 原应用中哪些使用过注解的引用可以放到当前切面中处理？(切点方法)
    // 1.1声明一个切点方法
    // 1.2补充切点方法注解规则 exxcution（注解全名，类、方法、参数）一下使用的是通配符，表示所有使用这个注解的类、方法、参数
    @Pointcut("execution(@org.alie.aopexplore.annotation.BehaviorTrace * *(..))")
    public void methodAnnotatedByBehaviorTrace() {
    }

    // 2 对进入切面的内容如何处理？ 也就是methodAnnotatedByBehaviorTrace 这个方法的具体处理方式
    // 2.1 自定义一个方法必须返回Object
    // 2.2.使用advice的语法来确定切点业务的作用区域
    // @Before() 在切点之前运行
    // @After() 在切点之后运行
    // @Around() 在切点前后都运行
    //以@Around（）注解举例 round中传入上面的那个方法
    @Around("methodAnnotatedByBehaviorTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {

        // 怎样获取所注释位置的相关信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); // 获取方法签名
        String className = methodSignature.getDeclaringType().getSimpleName();// 获取注解所在的类名
        String methodName = methodSignature.getName(); // 获取注解所在的方法名
        String fullName = methodSignature.getMethod().getAnnotation(BehaviorTrace.class).value(); // 获取注解名称

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // 这个可以理解为方法的执行的模拟（也就是说一旦调用 joinPoint.proceed（）及说明执行）
        long duration = System.currentTimeMillis() - begin;
        Log.d(MainActivity.TAG, String.format("功能:%s,%s类的%s方法执行了，用时%d ms", fullName, className, methodName, duration));
        return result;
    }

}
