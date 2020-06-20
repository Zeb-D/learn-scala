package com.yd.scala.elasticsearch

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * @author created by Zeb灬D on 2020-06-20 11:13
  */
@SpringBootApplication(scanBasePackages = Array("com.yd.scala.elasticsearch"))
class StartEsApp

object StartEsApp {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[StartEsApp],args:_*)
  }
}
