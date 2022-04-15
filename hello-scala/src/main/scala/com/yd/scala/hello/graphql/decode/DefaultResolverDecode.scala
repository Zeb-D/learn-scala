package com.yd.scala.hello.graphql.decode

import com.alibaba.fastjson.JSON
import com.yd.scala.hello.graphql.ProtoEnum
import com.yd.scala.hello.graphql.core.TraphResolver

/**
  * @author created by Zeb灬D on 2021-08-31 10:28
  */
class DefaultResolverDecode extends ResolverDecode[Any] {
  /**
    * 数据解析
    */
  override def decode(data: Any, resolver: TraphResolver): Any = resolver.proto match {
    case ProtoEnum.HTTP => http(data)
    case ProtoEnum.DUBBO => dubbo(data)
    case ProtoEnum.INJVM => inJvm(data)
  }

  private def inJvm(data: Any): Any = {
    return data
  }

  private def dubbo(data: Any): Any = {
    return data
  }

  private def http(data: Any): Any = {
    if (data.isInstanceOf[String]) {
      return try {
        return JSON.parseObject(data.asInstanceOf[String], classOf[Any])
      } catch {
        case ex: Exception => {
          println(ex)
        }
      }
    }
    return data
  }

  override def special(): String = {
    return "default"
  }
}
