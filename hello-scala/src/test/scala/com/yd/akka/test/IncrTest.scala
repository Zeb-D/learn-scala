package com.yd.akka.test

import org.junit.Test

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * 自增并发测试
  *
  * @author created by zouyd on 2019-07-21 21:47
  */
class IncrTest {

  @Test
  def testIncr() {
    var i, j = 0
    (1 to 100000).foreach { _ =>
      Future {
        i = i + 1
      }
    }
    (1 to 100000).foreach(_ => j = j + 1)
    Thread.sleep(1000)
    println(s"${i}\n${j}")
  }
}
