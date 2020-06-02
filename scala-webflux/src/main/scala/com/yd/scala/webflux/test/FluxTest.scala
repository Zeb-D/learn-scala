package com.yd.scala.webflux.test

import java.util

import reactor.core.publisher.Flux

/**
  * Flux 相当于一个 RxJava Observable 观察者
  * 观察者可以把生产者的消息Publisher,推送给消费者subscribe
  * <p>
  * 集合的Flux 对应数据是{0,N}
  * </p>
  *
  * @author created by Zeb-D on 2019-06-09 16:41
  */
object FluxTest {
  def main(args: Array[String]) {
    //两个生产者，一个订阅者
    Flux.just("1", "2", "3").subscribe((i: String) => println(i))
    Flux.fromArray(Array[Integer](112, 3, 4)).subscribe((i: Integer) => println(i))
    //空的
    Flux.empty
    //循环
    val list: Flux[String] = Flux.fromIterable(util.Arrays.asList("1", "2", "3", "4"))
    list.subscribe((i: String) => System.out.println(i))
  }
}
