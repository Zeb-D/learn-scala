package com.yd.scala.webflux.test

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.http.server.reactive.{HttpHandler, ReactorHttpHandlerAdapter}
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.{RouterFunctions, _}
import reactor.core.publisher.Mono
import reactor.ipc.netty.http.server.HttpServer

/**
  * @author created by zouyd on 2019-06-10 12:52
  */
@Configuration
class WebFluxRoutes {

  @Bean //为某个请求路径返回参数
  def webfluxGet: RouterFunction[ServerResponse] = {
    val data: Mono[String] = Mono.just("Hello webflux")
    return RouterFunctions.route(RequestPredicates.path("/v1.0/devices/"),
      request => ServerResponse.ok.body(data, classOf[String]))
  }

  //构建一个基于netty的web服务器
  private def webNetty(host: String, port: Int): Unit = {
    val helloNetty: RouterFunction[ServerResponse] = RouterFunctions.route(RequestPredicates.POST("/v1.0/devices/"),
      (request: ServerRequest) => ServerResponse.ok.body(BodyInserters.fromObject("Hello Netty Scala")))

    val httpHandler: HttpHandler = RouterFunctions.toHttpHandler(helloNetty)
    val adapter: ReactorHttpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler)
    HttpServer.create(host, port).newHandler(adapter);
  }


}
