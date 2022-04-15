package com.yd.scala.hello;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface Mask {
    int startMaskIndex() default -1;

    int endMaskIndex() default -1;

    boolean maskChinese() default false;
}