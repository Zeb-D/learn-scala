package com.yd.test.graphql

import com.yd.scala.hello.graphql.provider.JavaArgumentHelper
import org.junit.Test

/**
  * @author created by Zeb灬D on 2021-09-16 11:49
  */
class JavaArgumentHelperTest {

  @Test
  def TestArgType() ={
    println(JavaArgumentHelper.cacheClass)
    println(JavaArgumentHelper.scalar)
    println(JavaArgumentHelper.getScalar(classOf[Boolean]))
    println(JavaArgumentHelper.getScalar(classOf[String]))
  }
}
