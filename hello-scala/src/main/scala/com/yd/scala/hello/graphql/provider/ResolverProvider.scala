package com.yd.scala.hello.graphql.provider

import com.yd.scala.hello.graphql.ResolverArgument
import com.yd.scala.hello.graphql.core.TraphResolver

/**
  * @author created by Zeb灬D on 2021-09-15 19:32
  */
trait ResolverProvider {
  /**
    * 从缓存中获取 resolver, resolver在init时解析
    */
  def getResolver(id: String): TraphResolver

  /**
    * 获取所有的 resolver, map key 为 resolver id
    */
  def resolvers(): Map[String, TraphResolver]

  /**
    * 获取请求的的参数
    */
  def getArguments(id: String): List[ResolverArgument]

  /**
    * 获取请求所有的的参数
    */
  def getAllArguments(id: String): List[ResolverArgument]

  /**
    * @param id resolver id, 获取参数, 用于拼接参数
    * @return 返回类型过滤非 scalar 的参数
    */
  def getQueryArguments(id: String): List[ResolverArgument]
}
