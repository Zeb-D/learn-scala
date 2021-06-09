package com.yd.scala.helloscala

import com.yd.scala.hello.handler.{Event, Publish}
import javax.annotation.Resource
import org.junit.Test

/**
  * @author created by Zeb灬D on 2021-03-09 16:19
  */
class EventTest extends BaseTest {
  @Resource
  var publish: Publish = _


  @Test
  def TestPublish() {
    println(publish)
    val e = new Event
    e.setRequestId("1121212")
    publish.publish(e)
  }
}
