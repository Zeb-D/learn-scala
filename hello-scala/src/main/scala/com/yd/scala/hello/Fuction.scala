package com.yd.scala.hello

/**
  * 方法与参数
  * def 后面紧跟方法名称，参数列表，返回类型和方法体
  * 允许省略大括号和 return 关键字的。
  * 首先看下函数的定义，=> 左边为参数列表(这里的类型不能省略)，右边的为表达式内容
  *
  * 允许为函数参数指定默认值，这样即使调用函数过程中不传递参数，函数就是采用它的默认参数值
  * 支持不按照定义顺序，按照指定参数名字进行参数传递
  * 在参数类型后面放星号 *，表示可重复的参数
  *
  * @author created by Zeb-D on 2019-06-15 17:25
  */
object Fuction {
  private def add2(x: Int, y: Float = 1.0f): Int = {
    return x * 10 + y.toInt
  }
  def add3(args: Int*): Int = {
    args.sum
  }
  def add4(x: Int, y: Int): (Int,Int) ={
    (x+1,y+3)
  }
  def main(args: Array[String]): Unit = {
    val add = (x: Int, y: Int) => x + y
    println(add(11, 22))
    println(add2(1, 2))
    println(add2(y = 11.1f, x = 1))
    println(add2(123))
    println(add3(1, 2, 3, 45, 1))
    val t = add4(12,32)
    println(s"x=${t._1} ,y=${t._2}")
  }
}
