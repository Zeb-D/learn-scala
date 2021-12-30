package com.yd.scala.groovy


/**
  * @author created by Zeb灬D on 2020-06-25 12:14
  */
object Test {
  def main(args: Array[String]): Unit = {
    for (dir <- 0 to ScriptType1.maxId - 1) {
      print(dir)
      print(ScriptType1(dir) + "\t");
      println(ScriptType1(dir).id) //枚举值从0开始计数，可以用枚举值id方法获得它的计数值：
    }


  }
}
