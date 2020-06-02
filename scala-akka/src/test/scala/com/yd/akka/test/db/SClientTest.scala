package com.yd.akka.test.db

import com.yd.scala.akka.db.SClient
import org.junit.Test
import org.scalatest.Matchers

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * @author created by Zeb-D on 2019-08-04 18:30
  */
class SClientTest extends Matchers {
  val client = new SClient("127.0.0.1:2552")

  @Test
  def clientTest() {
    client.set("123", new Integer(123))
    val futureResult = client.get("123")
    val result = Await.result(futureResult, 10 seconds)
    result should equal(123)
  }

}
