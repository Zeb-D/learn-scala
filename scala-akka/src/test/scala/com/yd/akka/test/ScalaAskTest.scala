package com.yd.akka.test

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.yd.akka.ScalaPongActor
import org.junit.Test
import org.scalatest.Matchers

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class ScalaAskTest extends Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)

  import scala.concurrent.ExecutionContext.Implicits.global

  val pongActor = system.actorOf(Props(classOf[ScalaPongActor]), "zeb&d")

  @Test
  def test() {
    //向 Actor 请求一条消息的响应
    val future1 = pongActor ? "Ping" //akka.pattern.ask
    future1.onSuccess({
      case x: String => println("replied with: " + x)
    })

    //隐式传入的 Future 的超时参数:等待结果多久以后认为请求失败。
    val result = Await.result(future1.mapTo[String], 1 second)

    //两次发起请求，第一次发起后，再发起
    val f: Future[String] = askPong("Ping1").flatMap(x => askPong(x))
    f.onSuccess { case x: String => println("Success " + x) }
    //在失败情况下执行代码
    f.onFailure { case e: Exception => println("Got exception " + e) }
    //从失败中恢复:todo并不能在异常的时候给值返回
    f.recover {
      case e: Exception => "scala " + e.getMessage
      case je: java.lang.Exception => "java " + je.getMessage
      case _: Throwable => "default"
    }
    println("finally get:" + Await.result(f.mapTo[String], 1 second))


    assert(result == "Pong")
    //    result should equal("Pong")

    val future = pongActor ? "unknown"
    intercept[Exception] {
      Await.result(future.mapTo[String], 1 second)
    }
  }

  def askPong(message: String): Future[String] = (pongActor ? message).mapTo[String]
}