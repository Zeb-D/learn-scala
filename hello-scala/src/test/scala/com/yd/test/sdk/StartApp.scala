package com.yd.test.sdk


import com.yd.scala.hello.sdk.SdkScanner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.{RedisAutoConfiguration, RedisRepositoriesAutoConfiguration}
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.autoconfigure.task.{TaskExecutionAutoConfiguration, TaskSchedulingAutoConfiguration}


@SpringBootApplication(
  scanBasePackages = Array("com.yd.test.sdk", "com.yd.scala.hello.sdk"),
  exclude = Array(
    classOf[RedisAutoConfiguration],
    classOf[RedisRepositoriesAutoConfiguration],
    classOf[DataSourceAutoConfiguration],
    classOf[FreeMarkerAutoConfiguration],
    classOf[TaskSchedulingAutoConfiguration],
    classOf[TaskExecutionAutoConfiguration],
    classOf[KafkaAutoConfiguration])
)
@SdkScanner(basePackage = "com.yd.scala.hello.sdk.http")
class StartApp

object StartApp {
  def main(args: Array[String]) {
    SpringApplication.run(classOf[StartApp], args: _*)
  }
}