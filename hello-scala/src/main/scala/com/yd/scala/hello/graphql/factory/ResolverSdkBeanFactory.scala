package com.yd.scala.hello.graphql.factory

import com.yd.scala.hello.sdk.SdkManager

/**
  * @author created by Zeb灬D on 2021-09-14 20:41
  */
class ResolverSdkBeanFactory extends DefaultResolverBeanFactory {
  override def getObject[T](clazz: Class[T]): T = {
    return SdkManager.newProxyInstance(clazz).asInstanceOf
  }

  override def getObject(className: String): Any = {
    return getObject(Class.forName(className))
  }
}
