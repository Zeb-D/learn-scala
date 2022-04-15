package com.yd.scala.hello.graphql.core

import com.yd.scala.hello.graphql.{ProtoEnum, ResolverArgument}

/**
  * @author created by Zeb灬D on 2021-08-30 15:51
  */
class HttpResolver(
                    id: String,
                    name: String = null,
                    description: String = null,
                    decode: String,
                    val host: String,
                    val path: String,
                    val httpMethod: String,
                    val headers: List[ResolverArgument],
                    val arguments: List[ResolverArgument],
                    val body: ResolverArgument
                  ) extends TraphResolver(id, ProtoEnum.HTTP, name, description, decode) {

  import com.yd.scala.hello.graphql.ResolverArgument

  override def Arguments(): List[ResolverArgument] = {
    return arguments
  }

  override def AllArguments(): List[ResolverArgument] = {
    val allArgument = List().++(arguments).++(headers)
    if (body != null && body.dynamic) {
      return allArgument.:+(body)
    }
    return allArgument
  }
}
