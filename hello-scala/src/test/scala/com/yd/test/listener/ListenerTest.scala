package com.yd.test.listener

import com.yd.scala.hello.event.EventBusCenter
import com.yd.scala.hello.event.listener.DeviceEvent
import javax.annotation.Resource
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
  * @author created by Zeb灬D on 2021-03-09 16:19
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[StartApp]))
class ListenerTest {

  @Resource
  var eventBusCenter: EventBusCenter = _

  @Test
  def TestEvent(): Unit = {
    println(eventBusCenter)
    val e: DeviceEvent = new DeviceEvent()
    e.setDeviceId("111")
    e.setDeviceType(1)
    e.setEventType(2)
    e.setStatus("aaaa")
    eventBusCenter.postSync(e)
    println(e)
  }

}
