package com.yd.scala.hello.graphql.invoke

import com.yd.scala.hello.graphql.core.TraphResolver
import com.yd.scala.hello.graphql.decode.ResolverDecode

/**
  * @author created by Zeb灬D on 2021-09-14 17:22
  */
trait ResolverInvoke[A, R] {

  /**
    * TraphResolver 执行器
    *
    * @param traphResolver Resolver, 由[ResolverLoader]载入
    * @param args          调用当前Resolver 锁需要的参数
    * @param decode        调用方法后返回的参数解析的方式
    *                      目前有的执行器 DubboInvoke HttpInvoke  InJvmInvoke
    */
  def invoke(traphResolver: TraphResolver, args: A, decode: ResolverDecode[R]): R
}
