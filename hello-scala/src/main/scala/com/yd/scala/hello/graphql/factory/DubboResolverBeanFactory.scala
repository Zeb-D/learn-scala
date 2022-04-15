package com.yd.scala.hello.graphql.factory

import org.apache.dubbo.rpc.service.GenericService

/**
  * @author created by Zeb灬D on 2021-09-14 17:29
  */
trait DubboResolverBeanFactory extends ResolverBeanFactory {
  override def dubboObject(interfaceName: String, version: String): GenericService
}
