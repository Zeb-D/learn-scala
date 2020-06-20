package com.yd.scala.elasticsearch.controller

import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * @author created by Zeb灬D on 2020-06-20 11:10
  */
@RestController
class HelloController {

  @RequestMapping(value = Array("/hello"))
  def sayScalHello() = {
    "Hello scala boot..."
  }
}
