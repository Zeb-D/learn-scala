package com.yd.scala.grpc
import java.util.concurrent.SynchronousQueue
/**
  * @author created by zouyd on 2019-07-29 19:48
  */
object HelloGrpc {
  def main(args: Array[String]): Unit = {
    val queue = new SynchronousQueue[Integer]
    println(queue.offer(1) + " ")
    println(queue.offer(2) + " ")
    println(queue.offer(3) + " ")
    println(queue.take + " ")
    println(queue.size)
  }
}
