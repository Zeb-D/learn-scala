package com.yd.scala.hello

/**
  * 流程与条件
  *
  * @author created by Zeb-D on 2019-06-15 18:10
  */
object Condition {
  def numConvert2Str(str: Int): String = str match {
    case 1 => "one"
    case 2 => "two"
    case _ => "other"
  }

  def anyConvert2Str(any: Any): String = any match {
    case "aa" => "error"
    case p if(containsStr(p,"aa")) =>" contains aa"
    case str: String => str
    case int: Int => numConvert2Str(int)
    case _ => "other" //case _ 表示匹配其余所有情况，类似 switch-case 语法的 default 作用
  }

  def containsStr(any: Any, string: String): Boolean = {
    any.toString.contains(string)
  }

  def main(args: Array[String]) {
    println(s"x=1,result:${numConvert2Str(1)}")
    println(s"x=-1,result:${numConvert2Str(-1)}")
    println(s"x=aa,result:${anyConvert2Str("aa")}")
    var x = 1
    val result: String = x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "other"
    }
    println(s"x=$x,result:$result")
  }
}
