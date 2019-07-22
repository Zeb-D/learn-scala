package com.yd.entity

import akka.actor.{Actor, ActorLogging, ActorRef}

/**
  * @author created by zouyd on 2019-07-21 23:41
  */
class MyActor extends Actor with ActorLogging {
  var parentActorRef: ActorRef = _

  override def preStart(): Unit = {
    //通过context.parent获取其父Actor的ActorRef
    parentActorRef = context.parent
  }

  def receive = {
    case "test" => log.info("received test"); parentActorRef ! "message from ParentActorRef"
    case _ => log.info("received unknown message");
  }
}
