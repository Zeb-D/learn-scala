package com.yd.scala.hello.graphql.load

import java.io.{File, FileInputStream}
import java.net.URL

import com.yd.scala.hello.graphql.core.{ResolverRecipient, TraphResolverWrapper}
import org.slf4j.{Logger, LoggerFactory}
import org.yaml.snakeyaml.Yaml

import scala.collection.JavaConversions._

/**
  * @author created by Zeb灬D on 2021-09-15 14:31
  */
object ResolverLoader {
  private val yaml: Yaml = new Yaml()
  private val logger: Logger = LoggerFactory.getLogger("ResolverLoader")

  def getResource(path: String): URL = {
    val res: URL = Thread.currentThread().getContextClassLoader.getResource(path)
    if (res == null) {
      return path.getClass.getResource(path)
    }
    return res
  }

  def load(path: String): List[ResolverRecipient] = {
    logger.info(s"Resolver load path: ${path}")
    var result: List[ResolverRecipient] = List[ResolverRecipient]()
    val url = getResource(path)
    if (url == null) {
      return Nil;
    }
    val treeWalk: Array[File] = new File(url.toURI()).listFiles()
    treeWalk.foreach(
      f => {
        try {
          val loadAs: TraphResolverWrapper = yaml.loadAs(new FileInputStream(f), classOf[TraphResolverWrapper])
          if (loadAs != null && loadAs.resolvers != null && !loadAs.resolvers.isEmpty) {
            result = result.++(loadAs.resolvers.toList)
          }
          logger.info("Resolver load file : ${f.name} success ...")
        } catch {
          case e: Exception => {
            println(e)
            logger.error(s" Resolver load file:${f.getName} error ...", e)
          }
        }
      }
    )

    return result
  }
}
