package com.yd.scala.helloscala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.yd.scala")
public class HelloScalaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloScalaApplication.class, args);
	}

}
