package com.yd.scala.hello.handler.spring;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(YdaSelector.class)
public @interface EnableZebra {
    String[] scanPackages() default {};

    boolean isZebra() default true;


}


