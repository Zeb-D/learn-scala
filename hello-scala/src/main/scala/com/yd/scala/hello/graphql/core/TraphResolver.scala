package com.yd.scala.hello.graphql.core

import com.yd.scala.hello.graphql.ProtoEnum.ProtoEnum
import com.yd.scala.hello.graphql.ResolverArgument

/**
  * @author created by Zeb灬D on 2021-08-30 15:03
  */
@SerialVersionUID(1L)
abstract class TraphResolver(val id: String,
                             val proto: ProtoEnum,
                             val name: String = null,
                             val description: String = null,
                             val decode: String) {

  /**
    * 获取方法调用的参数, 不包含其他参数
    */
  def Arguments(): List[ResolverArgument]

  /**
    * 获取所有的参数, 包含请求头, 包含 attachments 等
    */
  def AllArguments(): List[ResolverArgument]
}
