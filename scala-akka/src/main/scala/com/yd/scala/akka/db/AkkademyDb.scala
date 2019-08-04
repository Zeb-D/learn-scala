package com.yd.scala.akka.db

import akka.actor.{Actor, Status}
import akka.event.Logging
import com.yd.entity.SetRequest
import com.yd.scala.akka.entity.{GetRequest, KeyNotFoundException}

import scala.collection.mutable.HashMap

class AkkademyDb extends Actor {
  //scala map ： map.get(key).get()
  val map = new HashMap[String, String]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value.toString)
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[Object] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(new KeyNotFoundException(key))
      }
    case o => Status.Failure(new ClassNotFoundException)
  }
}
