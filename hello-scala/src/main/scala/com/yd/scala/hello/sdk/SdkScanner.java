package com.yd.scala.hello.sdk;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
@Import(SdkScannerRegistrar.class)
public @interface SdkScanner {
    String basePackage();

    Class factoryBean() default SdkFactoryBean.class;
}
