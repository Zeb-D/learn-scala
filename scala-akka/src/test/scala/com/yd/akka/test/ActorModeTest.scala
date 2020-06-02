package com.yd.akka.test

import akka.actor.{ActorSystem, Props}
import com.yd.entity.FirstActor
import org.junit.Test

/**
  * https://blog.csdn.net/lovehuangjiaju/article/details/51045146
  * Actor引用、Actor路径
  * @author created by Zeb-D on 2019-07-21 23:43
  */
class ActorModeTest extends App {

  @Test
  def testAkka() {
    val system = ActorSystem("MyActorSystem")
    val systemLog = system.log

    //创建FirstActor对象
    val firstactor = system.actorOf(Props[FirstActor], name = "firstActor")

    //获取ActorPath
    val firstActorPath = system.child("firstActor")
    systemLog.info("firstActorPath--->{}", firstActorPath)

    //通过system.actorSelection方法获取ActorRef
    val myActor1 = system.actorSelection(firstActorPath)

    //直接指定其路径
    val myActor2 = system.actorSelection("/user/firstActor")
    //使用相对路径
    val myActor3 = system.actorSelection("../firstActor")

    systemLog.info("准备向myactor发送消息")
    //向myActor1发送消息
    myActor1 ! "test"
    myActor2 ! 123
    Thread.sleep(5000)
    //关闭ActorSystem，停止程序的运行
    system.shutdown()
  }
}
