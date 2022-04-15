package com.yd.scala.hello.graphql

/**
  * @author created by Zeb灬D on 2021-08-30 15:21
  */
class ResolverArgument(
                        val name: String,
                        val clazz: Class[_],
                        val dynamic: Boolean,
                        val value: Any) {

}
