package com.yd.scala.hello

import org.springframework.web.bind.annotation.{GetMapping, RestController}

@RestController
class HelloContrroller {

  @GetMapping(value = Array("/hello"))
  def hello(): String = {
    "hell scala"
  }

}

object HelloContrroller {

}
