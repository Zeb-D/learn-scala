package com.yd.scala.hello.graphql.decode

import com.yd.scala.hello.graphql.core.TraphResolver

/**
  * @author created by Zeb灬D on 2021-08-31 10:27
  */
trait ResolverDecode[T] {
  /**
    * 数据解析
    */
  def decode(data: Any, resolver: TraphResolver): T

  /**
    * 当前 decode 适合哪种格式, 用于扩展,
    * 目前用 proto 来分类
    * 提供通过 special 来做 decode
    * dubbo
    * http
    * injvm
    */
  def special(): String
}
