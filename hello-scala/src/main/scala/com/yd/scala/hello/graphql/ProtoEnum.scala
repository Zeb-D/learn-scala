package com.yd.scala.hello.graphql

/**
  * @author created by Zeb灬D on 2021-08-30 15:05
  */
object ProtoEnum extends Enumeration {
  type ProtoEnum = Value
  val DUBBO, HTTP, INJVM = Value

  def get(str: String): ProtoEnum = {
    for (x <- ProtoEnum.values) {
      if (x.toString.eq(str)) {
        return x
      }
    }
    return null
  }

  def main(args: Array[String]): Unit = {
    for (x <- ProtoEnum.values) {
      println(s"${x.id}  ${x}")
    }
    println(get("DUBBO"))
    println(get("DUBBO1"))

    val list: List[Int] = List(1, 2, 4) //不能null
    val list1: List[Int] = List(3, 4)
    val list2 = List().++(list).++(list1)

    println(list)
    println(list1)
    println(list2)
  }
}
