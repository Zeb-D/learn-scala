package com.yd.scala.groovy

/**
  * @author created by Zeb灬D on 2020-06-25 12:05
  */
object ScriptType1 extends Enumeration {
  //这行是可选的，类型别名，在使用import语句的时候比较方便，建议加上
  //枚举的定义
  val FILE = Value(0, "文件") //0
  val TEXT = Value(1, "文本") //1

  def checkExists(scriptType: Int) = this.values.exists(_.id == scriptType) //检测是否存在此枚举值
  def showAll = this.values.foreach(println) // 打印所有的枚举值
}
