package com.yd.scala.helloscala

import com.yd.scala.hello.StartApp
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
  * @author created by Zeb灬D on 2019-12-24 10:59
  */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes = Array(classOf[StartApp]))
class BaseTest
