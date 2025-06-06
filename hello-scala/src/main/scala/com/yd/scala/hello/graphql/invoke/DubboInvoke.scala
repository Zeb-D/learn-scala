package com.yd.scala.hello.graphql.invoke

import java.util

import com.yd.scala.hello.graphql.core.{DubboResolver, TraphResolver}
import com.yd.scala.hello.graphql.decode.ResolverDecode
import com.yd.scala.hello.graphql.factory.DubboResolverBeanFactory
import org.apache.dubbo.rpc.RpcContext

/**
 * @author created by Zeb灬D on 2021-09-14 21:01
 */
class DubboInvoke[R](val factory: DubboResolverBeanFactory) extends ResolverInvoke[Map[String, Any], R] {
  /**
   * TraphResolver 执行器
   *
   * @param traphResolver Resolver, 由[ResolverLoader]载入
   * @param args          调用当前Resolver 锁需要的参数
   * @param decode        调用方法后返回的参数解析的方式
   *                      目前有的执行器 DubboInvoke HttpInvoke  InJvmInvoke
   */
  override def invoke(traphResolver: TraphResolver, args: Map[String, Any], decode: ResolverDecode[R]): R = {
    val resolver: DubboResolver = traphResolver.asInstanceOf[DubboResolver]
    val param: util.ArrayList[Any] = new util.ArrayList[Any]()
    val argsType: util.ArrayList[String] = new util.ArrayList()
    for (argument <- resolver.arguments) {
      if (argument.dynamic) {
        param.add(args.get(argument.name))
      } else {
        param.add(argument.value)
      }
      argsType.add(argument.clazz.getName)
    }
    val parameterTypes: Array[String] = argsType.toArray(new Array[String](1))
    for (attachment <- resolver.attachments) {
      if (attachment.dynamic) {
        val arg = args.get(attachment.name)
        if (arg.nonEmpty) {
          RpcContext.getContext().setAttachment(attachment.name, arg.get.toString)
        }
      } else {
        if (attachment.value != null) {
          RpcContext.getContext().setAttachment(attachment.name, attachment.value.toString())
        } else {
          RpcContext.getContext().setAttachment(attachment.name, null)
        }
      }
    }
    val bean = factory.dubboObject(resolver.interfaceClass, resolver.version)
    val result = bean.`$invoke`(resolver.method, parameterTypes, param.toArray())
    return decode.decode(result, resolver)
  }
}
