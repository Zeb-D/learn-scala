package com.yd.scala.hello.graphql.factory

/**
  * invoke 调用方法所需要的对象
  *
  * @author created by Zeb灬D on 2021-09-14 17:26
  */
trait ResolverBeanFactory {
  /**
    * injvm 获取调用的bean的方法
    */
  def getObject[T](clazz: Class[T]): T

  /**
    * injvm 获取调用的bean的方法
    */
  def getObject(className: String): Any

  /**
    * dubbo 获取调用的bean的方法
    */
  def dubboObject(interfaceName: String, version: String): Any
}
