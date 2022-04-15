package com.yd.test.template

import java.util

import com.yd.scala.hello.template.TemplateUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
  * @author created by Zeb灬D on 2021-03-09 16:19
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[StartApp]))
class TemplateTest {

  @Test
  def TestTemplate() {
    val map: util.HashMap[String, Object] = new util.HashMap;
    map.put("name", "yd")
    map.put("time", System.currentTimeMillis().toString)
    println(TemplateUtil.byTemplate("template/hello.twig", map))

    map.put("messageId", System.currentTimeMillis().toString)
    map.put("uid", "Yd")
    println(TemplateUtil.byTemplate("template/error_response.twig", map))
    map.put("intent", "query")
    println(TemplateUtil.byTemplate("template/error_response.twig", map))
    map.put("intent", "control")
    println(TemplateUtil.byTemplate("template/error_response.twig", map))
  }

  @Test
  def TestJsonPath() {
    val jsonString = "{\n" + "    \"u\": {\n" + "        \"id\": \"T001\",\n" + "        \"name\": \"test\"\n" + "    }\n" + "}"
    val userInfo = TemplateUtil.byJsonPath(classOf[UserInfo], jsonString)
    println(userInfo)
  }
}
