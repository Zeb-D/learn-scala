package com.yd.scala.hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * @author created by Zeb灬D on 2019-12-24 10:49
  */
@SpringBootApplication(scanBasePackages = Array("com.yd.scala")) //该注解不能静态类
class StartApp


/**
  * @author created by Zeb灬D on 2019-12-24 10:49
  */
object StartApp {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[StartApp],args:_*)
  }
}
