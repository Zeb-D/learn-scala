package com.yd.akka.test

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.yd.akka.AkkademyDb
import com.yd.entity.SetRequest
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

/**
  * 使用scala测试AkkademyDb
  *
  * @author created by zouyd on 2019-07-21 22:36
  */
//class AkkademyDbSpec extends FunSpecLike with Matchers {
//  implicit val system = ActorSystem()
//  implicit val timeout = Timeout(5 seconds)
//
//  describe("akkademyDb") {
//    describe("given SetRequest") {
//      it("should place key/value into map") {
//        val actorRef = TestActorRef(new AkkademyDb)
//        actorRef ! SetRequest("key", "value")
//        val akkademyDb = actorRef.underlyingActor
//        akkademyDb.map.get("key") should equal(Some("value"))
//      }
//    }
//  }
//}

