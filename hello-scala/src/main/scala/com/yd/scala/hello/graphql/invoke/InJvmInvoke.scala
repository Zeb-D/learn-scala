package com.yd.scala.hello.graphql.invoke

import java.util

import com.yd.scala.hello.graphql.core.{JvmResolver, TraphResolver}
import com.yd.scala.hello.graphql.decode.ResolverDecode
import com.yd.scala.hello.graphql.factory.ResolverBeanFactory

/**
  * @author created by Zeb灬D on 2021-09-14 20:47
  */
class InJvmInvoke[R](val factory: ResolverBeanFactory) extends ResolverInvoke[Map[String, Any], R] {
  /**
    * TraphResolver 执行器
    *
    * @param traphResolver Resolver, 由[ResolverLoader]载入
    * @param args          调用当前Resolver 锁需要的参数
    * @param decode        调用方法后返回的参数解析的方式
    *                      目前有的执行器 DubboInvoke HttpInvoke  InJvmInvoke
    */
  override def invoke(traphResolver: TraphResolver, args: Map[String, Any], decode: ResolverDecode[R]): R = {
    val resolver: JvmResolver = traphResolver.asInstanceOf[JvmResolver]

    val param: util.ArrayList[Any] = new util.ArrayList()
    val argsType: util.ArrayList[Class[_]] = new util.ArrayList()
    for (argument <- resolver.arguments) {
      if (argument.dynamic) {
        param.add(args.get(argument.name))
      } else {
        param.add(argument.value)
      }
      argsType.add(argument.clazz)
    }
    val bean = factory.getObject(className = resolver.className)

    val array = argsType.toArray(new Array[Class[Any]](1))
    val method = bean.getClass.getMethod(resolver.methodName, array: _*)

    val result: Any = method.invoke(bean, param.toArray(): _*)
    return decode.decode(result, resolver)
  }
}
