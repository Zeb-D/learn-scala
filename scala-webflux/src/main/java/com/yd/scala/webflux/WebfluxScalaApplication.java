package com.yd.scala.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yd.scala")
public class WebfluxScalaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxScalaApplication.class, args);
    }
}
