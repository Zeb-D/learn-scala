package com.yd.scala.hello.graphql.provider

import java.util

import com.yd.scala.hello.graphql.ResolverArgument
import com.yd.scala.hello.graphql.core.TraphResolver
import com.yd.scala.hello.graphql.load.ResolverLoader
import org.slf4j.{Logger, LoggerFactory}

/**
  * @author created by Zeb灬D on 2021-09-15 18:37
  */

class TraphResolverProvider(path: String = "resolver", paths: List[String] = List()) extends ResolverProvider {
  val cacheResolver: util.Map[String, TraphResolver] = new util.HashMap[String, TraphResolver]()
  val logger: Logger = LoggerFactory.getLogger(classOf[TraphResolverProvider]);
  {
    paths.+(path)
    paths.foreach(
      p => {
        val list = try {
          //载入只负责载入, 符合yaml 格式就可以
          ResolverLoader.load(p)
        } catch {
          case e: Exception => {
            println(e)
            logger.error(s" Resolver load file:${p} error ...", e)
          }
            List()
        }

      }
    )
  }

  /**
    * 从缓存中获取 resolver, resolver在init时解析
    */
  override def getResolver(id: String): TraphResolver = ???

  /**
    * 获取所有的 resolver, map key 为 resolver id
    */
  override def resolvers(): Map[String, TraphResolver] = ???

  /**
    * 获取请求的的参数
    */
  override def getArguments(id: String): List[ResolverArgument] = ???

  /**
    * 获取请求所有的的参数
    */
  override def getAllArguments(id: String): List[ResolverArgument] = ???

  /**
    * @param id resolver id, 获取参数, 用于拼接参数
    * @return 返回类型过滤非 scalar 的参数
    */
  override def getQueryArguments(id: String): List[ResolverArgument] = ???
}
