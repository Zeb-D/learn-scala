package com.yd.test.handler

import java.util

import com.yd.scala.hello.handler.cluster.ClusterConfig
import com.yd.scala.hello.handler.protocol.Protocol
import javax.annotation.Resource
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
  * @author created by Zeb灬D on 2021-03-09 16:19
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[StartApp]))
class YdaTest {

  @Resource
  var email: Protocol = _
  @Resource
  var phone: Protocol = _
  @Resource
  var clusterConfig: ClusterConfig = _
  @Resource
  var zebraService: ZebraService = _
  @Value("${mysql.password:yd_mysql}")
  var password: String = _
  @Value("#{'${server.name:}'.split(',')}")
  var servers: List[String] = _
  @Value("#{${test.maps:{}}}")
  var maps: util.Map[String, String] = _

  @Test
  def TestValue(): Unit = {
    println(password)
    println(servers)
    println(maps)
  }

  @Test
  def TestZebra(): Unit = {
    println(zebraService)
    println(zebraService.sayUser("Yd").pwd)
  }

  @Test
  def TestPublish() {
    println(email)
    println(phone)
    println(phone.getHider.hide("1234"))
    println(clusterConfig)
  }
}
