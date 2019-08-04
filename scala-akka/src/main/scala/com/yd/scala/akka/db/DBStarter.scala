package com.yd.scala.akka.db

import akka.actor.{ActorSystem, Props}

/**
  * @author created by zouyd on 2019-08-04 18:36
  */
object DBStarter extends App {
  val system = ActorSystem("akkademy")
  val helloActor = system.actorOf(Props[AkkademyDb], name = "akkademy-db")
}
