package com.yd.entity

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

class FirstActor extends Actor with ActorLogging {
  //通过context.actorOf方法创建Actor
  var child: ActorRef = _

  override def preStart(): Unit = {
    log.info("preStart() in FirstActor")
    //通过context上下文创建Actor
    child = context.actorOf(Props[MyActor], name = "myActor")
  }

  def receive = {
    //向MyActor发送消息
    case x => child ! x; log.info("received " + x)
  }




}
