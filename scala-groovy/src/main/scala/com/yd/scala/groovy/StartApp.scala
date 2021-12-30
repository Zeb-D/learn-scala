package com.yd.scala.groovy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * @author created by Zeb灬D on 2020-06-20 11:13
  */
@SpringBootApplication(scanBasePackages = Array("com.yd.scala.groovy"))
class StartApp

object StartApp {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[StartApp], args: _*)
  }
}
