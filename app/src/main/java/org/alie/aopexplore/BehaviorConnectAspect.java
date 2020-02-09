package org.alie.aopexplore;

import android.util.Log;

import org.alie.aopexplore.annotation.BehaviorConnectFunction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by Alie on 2020/2/9.
 * 类描述
 * 版本
 */
@Aspect
public class BehaviorConnectAspect {
    @Pointcut("execution(@org.alie.aopexplore.annotation.BehaviorConnectFunction * *(..))")
    public void executeCutPoint() {
    }

    @Around("executeCutPoint()")
    public Object doExecuteCutPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        String anotaiton = method.getAnnotation(BehaviorConnectFunction.class).name();

        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            String value = (String) arg;
            Log.d(MainActivity.TAG, String.format("功能:%s,%s类的%s方法,传入参数值：%s;执行了，用时%d ms", anotaiton, className, methodName, value,duration));
        }

        return object;
    }
}
