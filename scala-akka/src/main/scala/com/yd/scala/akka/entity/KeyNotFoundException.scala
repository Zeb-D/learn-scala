package com.yd.scala.akka.entity

//Scala 的 case class 是可以被序列化的。
case class KeyNotFoundException(key: String) extends Exception