package com.example.springdemo.annotation;

/**
 * date: 2020/12/3 11:51 description:
 *
 * @author dongyu.ye
 * @since 3.0.0
 */
public @interface AnnotationWithInherited {

    String value() default "";
}
