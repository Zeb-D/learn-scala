package com.yd.scala.hello.graphql.core

import com.yd.scala.hello.graphql.{ProtoEnum, ResolverArgument}

/**
  * @author created by Zeb灬D on 2021-08-30 21:43
  */
class JvmResolver(
                   id: String,
                   name: String = "",
                   description: String = "",
                   decode: String,

                   /**
                     * jvm 调用的对象
                     */
                   val className: String,

                   /**
                     * 被调用对象的方法
                     */
                   val methodName: String,
                   val arguments: List[ResolverArgument]
                 ) extends TraphResolver(id, ProtoEnum.INJVM, name, description, decode) {


  override def Arguments(): List[ResolverArgument] = {
    return arguments
  }

  override def AllArguments(): List[ResolverArgument] = {
    return arguments
  }
}
