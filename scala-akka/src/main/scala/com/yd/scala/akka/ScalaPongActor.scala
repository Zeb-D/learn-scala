package com.yd.akka

import akka.actor.{Actor, Props, Status}

class ScalaPongActor extends Actor {
  override def receive: Receive = {
    case "Ping" => sender() ! "Pong"
    case "Pong" => sender() ! "Ping"
    case _ =>
      sender() ! Status.Failure(new Exception("unknown message"))
  }

  //指定Actor名称
  def props(name: String): Props = {
    Props(classOf[ScalaPongActor], name)
  }
}
