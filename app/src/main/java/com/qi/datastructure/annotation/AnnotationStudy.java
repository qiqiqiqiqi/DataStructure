package com.qi.datastructure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by feng on 2020/4/8.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationStudy {
    String value() default "";
}
