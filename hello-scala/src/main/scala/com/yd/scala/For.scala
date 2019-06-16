package com.yd.scala

/**
  * 循环语法比较特别，使用 for + <- 来进行遍历元素，并提供了便捷的 until方法，to方法来实现遍历,
  * 需要注意的就是 until方法采用半闭区间，不包含索引最后一位。而 until方法，to方法底层都是构建 Range 对象然后进行遍历。
  *
  * @author created by zouyd on 2019-06-16 15:03
  */
object For {
  def main(args: Array[String]): Unit = {
    val numbers = Array(1, 2, 3, 4, 5)
    for (it <- numbers) print(it)
    println()
    for (i <- 0 until numbers.length) print(i)
    println()
    for (i <- numbers if i % 2 == 0) print(i)
    println()
    for (i <- Range(0, numbers.length)) print(i)
    val a = List((1, 2), (22, 33))
    for ((x, y) <- a) println(s"x=$x y=$y")
  }
}
