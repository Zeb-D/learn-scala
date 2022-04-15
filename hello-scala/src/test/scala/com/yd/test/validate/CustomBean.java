package com.yd.test.validate;

import org.springframework.stereotype.Component;

@Component
public class CustomBean {
    public CustomBean() {
        System.out.println("调用CustomBean空的构造方法");
    }
}