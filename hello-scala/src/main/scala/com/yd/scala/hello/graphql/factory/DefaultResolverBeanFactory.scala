package com.yd.scala.hello.graphql.factory

import java.util.concurrent.ConcurrentHashMap

import org.apache.dubbo.config.ReferenceConfig
import org.apache.dubbo.rpc.service.GenericService

/**
  * @author created by Zeb灬D on 2021-09-14 18:07
  */
class DefaultResolverBeanFactory extends DubboResolverBeanFactory {
  val cacheBean: ConcurrentHashMap[String, Any] = new ConcurrentHashMap()

  override def dubboObject(interfaceName: String, version: String): GenericService = {
    return cacheBean.putIfAbsent(interfaceName + "-" + version, {
      val reference: ReferenceConfig[GenericService] = new ReferenceConfig()
      reference.setInterface(interfaceName)
      if (version != null) {
        reference.setVersion(version)
      }
      reference.setGeneric("true")
      reference.get()
    }).asInstanceOf[GenericService]
  }

  /**
    * injvm 获取调用的bean的方法
    */
  override def getObject[T](clazz: Class[T]): T = {
    return cacheBean.putIfAbsent(clazz.getName, clazz.newInstance()).asInstanceOf[T]
  }

  /**
    * injvm 获取调用的bean的方法
    */
  override def getObject(className: String): Any = {
    return getObject(Class.forName(className))
  }
}
