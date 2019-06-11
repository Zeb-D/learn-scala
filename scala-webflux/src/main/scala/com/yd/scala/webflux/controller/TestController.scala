package com.yd.scala.webflux.controller

import com.yd.scala.webflux.config.TestHandler
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.reactive.function.server._

/**
  * @author created by zouyd on 2019-06-10 14:45
  */
@Configuration
class TestController {


  @Bean
  def testHandler(): TestHandler = new TestHandler()

  @Bean
  def routes(testHandler: TestHandler): RouterFunction[ServerResponse] =
    RouterFunctions.route(RequestPredicates.POST("/route"),
      request => testHandler.echoName(request))

  @GetMapping(Array("/anno"))
  def sayHello(name: String): String = "hello world! " + name


}


