package org.alie.aopexplore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Alie on 2019/7/13.
 * 用来标识哪些地方需要进行性能检测（AOP）
 * 1.声明BehaviorTrace是个注解使用 @interface表示
 * 2.声明BehaviorTrace能写在哪些地方使用@Target()
 *  2.1ElementType.METHOD 表示这个注解可以写在方法上
 * 3.注解的存活时间
 *   source,class,runtime
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BehaviorTrace {
        String value();// 来自注解的父类 主要用于生命注解的时候，传入字符串用来区分注解
}
