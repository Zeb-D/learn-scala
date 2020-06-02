package com.yd.akka.test

import akka.actor.ActorSystem
import akka.util.Timeout
import com.yd.entity.SetRequest
import org.junit.Test

import scala.concurrent.Future
import scala.concurrent.duration._


/**
  * actorSelection 获取指向 Actor 的引用
  *
  * @author created by Zeb-D on 2019-07-28 23:13
  */
class ActorSelectionTest {
  private[test] val system = ActorSystem.create
  implicit val timeout = Timeout(5 seconds)


  @Test
  def ActorSelection() = {
    val selection = system
      .actorSelection("akka.tcp:// actorSystem@host.jason-goodwin.com:5678/user/KeanuReeves")
    selection ! SetRequest("key", "value")
    val future =selection.resolveOne()
//    future.fallbackTo()
  }
}
