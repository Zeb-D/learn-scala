package com.yd.scala.hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.{RedisAutoConfiguration, RedisRepositoriesAutoConfiguration}
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
  * @author created by Zeb灬D on 2019-12-24 10:49
  */
@SpringBootApplication(
  scanBasePackages = Array("com.yd.scala.hello"),
  exclude = Array(
    classOf[RedisAutoConfiguration],
    classOf[RedisRepositoriesAutoConfiguration],
    classOf[DataSourceAutoConfiguration],
    classOf[FreeMarkerAutoConfiguration],
    classOf[KafkaAutoConfiguration])
) //该注解不能静态类
@EnableTransactionManagement
class StartApp


/**
  * @author created by Zeb灬D on 2019-12-24 10:49
  */
object StartApp {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[StartApp], args: _*)
  }
}
