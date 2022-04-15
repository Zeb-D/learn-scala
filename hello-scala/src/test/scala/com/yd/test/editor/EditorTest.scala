package com.yd.test.editor

import java.util

import com.alibaba.fastjson.JSON
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
class EditorTest {
  @Value("${mysql.password:yd_mysql}")
  var password: String = _
  @Value("#{'${server.name:}'.split(',')}")
  var servers: util.List[String] = _ //scala list不支持啊
  @Value("#{${test.maps:{'a':1,'b':'b'}}}")
  var maps: util.HashMap[String, String] = _

  @Value("#{${test.maps:}}")
  var json: JSON = _

  @Test
  def TestValue(): Unit = {
    println(password)
    println(servers)
    println(maps)
    println(json)
  }
}
