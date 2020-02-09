package org.alie.aopexplore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Alie on 2020/2/9.
 * 类描述
 * 版本
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BehaviorConnectFunction {
    String name();
}
