package com.yd.scala.webflux.test

import java.time.Duration

import reactor.core.publisher.Mono

/**
  * 可以把Mono理解为一个结果它对应的数据是 1
  * Mono和flux 的设计类似类似于RxJava
  * 基于事件模式或者订阅者模式
  * 可以产生一个消息，多个消费者
  *
  * @author created by Zeb-D on 2019-06-09 16:01
  */
object MonoTest {
  def main(args: Array[String]) {
    Mono.from(Mono.just("start mono"))
      // 第一个消费
      .doOnNext((i: String) => println("1 msg:" + i))
      //第二个消费
      .doOnNext((s: String) => {
      try
        print(1 / 0)
      catch {
        case e: Exception =>
          throw new RuntimeException("模拟异常")
      }
      println("2 msg:" + s)
    })
      .doOnNext((s: String) => System.out.println("3正常msg:" + s))
      .doOnError((s: Throwable) => System.out.println("进入消费异常：" + s))
      .block(Duration.ofMinutes(10))
  }

}
