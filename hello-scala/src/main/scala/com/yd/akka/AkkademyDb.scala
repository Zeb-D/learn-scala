package com.yd.akka

import akka.actor.Actor
import akka.event.Logging
import com.yd.entity.SetRequest

import scala.collection.mutable.HashMap

class AkkademyDb extends Actor {
  //scala map ： map.get(key).get()
  val map = new HashMap[String, String]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value.toString)
    }
    case o => log.info("received unknown message: {}", o);
  }
}
