package com.yd.scala.hello.graphql.invoke

import java.util.regex.Pattern

import com.fasterxml.jackson.databind.ObjectMapper
import com.squareup.okhttp.{MediaType, Request, RequestBody}
import com.yd.scala.hello.graphql.core.{HttpResolver, TraphResolver}
import com.yd.scala.hello.graphql.decode.ResolverDecode
import com.yd.scala.hello.sdk.{HttpConfig, SdkManager}
import org.slf4j.{Logger, LoggerFactory}

/**
  * @author created by Zeb灬D on 2021-09-14 21:16
  */
class HttpInvoke[R](httpConfig: HttpConfig) extends ResolverInvoke[Map[String, Any], R] {
  val client = SdkManager.buildHttpClient(httpConfig)
  val logger: Logger = LoggerFactory.getLogger(classOf[HttpInvoke[R]])

  /**
    * TraphResolver 执行器
    *
    * @param traphResolver Resolver, 由[ResolverLoader]载入
    * @param args          调用当前Resolver 锁需要的参数
    * @param decode        调用方法后返回的参数解析的方式
    *                      目前有的执行器 DubboInvoke HttpInvoke  InJvmInvoke
    */
  override def invoke(traphResolver: TraphResolver, args: Map[String, Any], decode: ResolverDecode[R]): R = {
    val resolver: HttpResolver = traphResolver.asInstanceOf[HttpResolver]
    val request: Request = buildRequest(resolver, args)
    val response = client.newCall(request).execute()
    return decode.decode(response.body.string(), resolver)
  }

  def buildRequest(resolver: HttpResolver, args: Map[String, Any]): Request = {
    val builder = new Request.Builder()
    resolver.headers.foreach {
      it => {
        if (it.dynamic) {
          builder.addHeader(it.name, String.valueOf(args.get(it.name)))
        } else {
          builder.addHeader(it.name, it.value.toString())
        }
      }
    }
    //替换路径的参数
    val path: String = buildPath(resolver.path, args)
    var param = ""
    args.foreach(it => {
      param += it._1 + "=" + it._2 + "&"
    })
    var body: RequestBody = null
    val bodyArg = resolver.body
    if (bodyArg != null) {
      val value = args.get(bodyArg.name)
      if (bodyArg.dynamic && value != null) {
        if (value.nonEmpty && value.get.isInstanceOf[String]) {
          body = RequestBody.create(MediaType.parse("application/json"), value.get.asInstanceOf[String])
        } else {
          body = RequestBody.create(MediaType.parse("application/json"), toJson(value))
        }
      } else {
        body = RequestBody.create(MediaType.parse("application/json"), bodyArg.value.asInstanceOf[String])
      }
    }
    param = if (param.isEmpty) "" else "?" + param.substring(0, param.length - 1)
    builder.method(resolver.httpMethod, body)
      .url(resolver.host + path + param)
    return builder.build()
  }

  val objectMapper: ObjectMapper = new ObjectMapper()

  def toJson(any: Any): String = {
    if (any == null) return ""
    return objectMapper.writeValueAsString(any)
  }

  val pattern = Pattern.compile("(\\{.+?})")

  def buildPath(path: String, args: Map[String, Any]): String = {
    val matcher = pattern.matcher(path)
    var newPath = path
    while (matcher.find()) {
      val group = matcher.group()
      val value: Any = args.get(group.substring(1, group.length - 1))
      if (value != null) {
        newPath = path.replace(group, value.toString())
      }
    }
    return newPath
  }
}
