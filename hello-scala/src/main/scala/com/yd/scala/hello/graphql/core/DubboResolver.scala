package com.yd.scala.hello.graphql.core

import com.yd.scala.hello.graphql.{ProtoEnum, ResolverArgument}

/**
  * @author created by Zeb灬D on 2021-08-30 15:39
  */
class DubboResolver(
                     id: String,
                     name: String = "",
                     description: String = "",
                     decode: String,
                     val interfaceClass: String,
                     val method: String,
                     val arguments: List[ResolverArgument],
                     val attachments: List[ResolverArgument],
                     val version: String
                   ) extends TraphResolver(id, ProtoEnum.DUBBO, name, description, decode) {

  import com.yd.scala.hello.graphql.ResolverArgument

  override def Arguments(): List[ResolverArgument] = {
    return this.arguments
  }

  override def AllArguments(): List[ResolverArgument] = {
    return this.arguments.++(attachments)
  }
}
