package com.yd.test.extension;

import com.yd.scala.hello.extension.annotation.ExtensionPoint;
import org.springframework.stereotype.Component;


@Component
public class DemoService {

    @ExtensionPoint(name = "demo_test")
    public String test(String dealerId, String phone) {
        return "DemoService#test";
    }
}
