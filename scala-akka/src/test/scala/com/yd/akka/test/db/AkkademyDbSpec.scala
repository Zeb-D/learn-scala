package com.yd.akka.test.db

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.yd.entity.SetRequest
import com.yd.scala.akka.db.AkkademyDb
import org.junit.Test
import org.scalatest.Matchers

import scala.concurrent.duration._

/**
  * 使用scala测试AkkademyDb
  *
  * @author created by Zeb-D on 2019-07-21 22:36
  */
class AkkademyDbSpec extends Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  @Test
  def test() {
    //创建一个Actor
    val actorRef = TestActorRef(new AkkademyDb)
    //该Actor发送一个异步消息
    actorRef ! SetRequest("key", "value")
    //获取该Actor实际引用
    val akkademyDb = actorRef.underlyingActor
    //判断与发送的值
    akkademyDb.map.get("key") should equal(Some("value"))
  }
}

