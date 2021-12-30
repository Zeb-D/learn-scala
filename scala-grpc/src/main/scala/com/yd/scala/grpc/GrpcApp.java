package com.yd.scala.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author created by Zeb-D on 2019-08-19 11:38
 */
@SpringBootApplication(scanBasePackages = "com.yd.scala")
public class GrpcApp {
    public static void main(String[] args) {
        SpringApplication.run(GrpcApp.class, args);
    }
}
