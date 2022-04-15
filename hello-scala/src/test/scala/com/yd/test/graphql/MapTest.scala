package com.yd.test.graphql

import java.util

import org.junit.Test

import scala.collection.JavaConverters._


/**
  * @author created by Zeb灬D on 2021-09-17 10:51
  */
class MapTest {

  @Test
  def TestMap() = {
    val v: TestVO = new TestVO();
    val a: List[Map[String, AnyRef]] = List[Map[String, AnyRef]]();
    import scala.collection.JavaConversions._
//    val c: java.util.List[java.util.Map[String, AnyRef]] = a.asJava
    println(a)
    println(v)

    // 创建 scala list
    var scalaList = List("1", "2", "3")
    // scala list 转 java list
    val javaList: util.List[String] = scalaList.asJava
    // java list 转 Scala list
    scalaList = javaList.asScala.toList
    println(scalaList)
  }
}
