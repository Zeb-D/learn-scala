//package com.yd.scala.hello.graphql.provider
//
//import java.util
//import java.util.Map
//
//import com.yd.scala.hello.graphql.core._
//import com.yd.scala.hello.graphql.{ProtoEnum, ResolverArgument}
//
///**
//  * @author created by Zeb灬D on 2021-09-15 19:55
//  */
//object ResolverParser {
//  /**
//    * 解析配置文件接收到的模板
//    */
//  def parser(recipient: ResolverRecipient): TraphResolver = {
//    ProtoEnum.get(recipient.getProto) match {
//      case ProtoEnum.DUBBO => {
//        dubboParser(recipient)
//      }
//      case ProtoEnum.HTTP => {
//        httpParser(recipient)
//      }
//      case ProtoEnum.INJVM => {
//        inJvmParser(recipient = recipient)
//      }
//      case _ => null
//    }
//  }
//
//
//  /**
//    * 解析为jvm本地调用
//    */
//  def inJvmParser(recipient: ResolverRecipient): TraphResolver = {
//    val id = recipient.getId
//    val name = recipient.getName
//    val description = recipient.getDescription
//    val interfaceClass = recipient.getInterfaceClass
//    val methodName = recipient.getMethod
//    val arguments = recipient.getArguments
//    val decode: String = recipient.getDecode
//    val args: List[ResolverArgument] = List[ResolverArgument]()
//    argumentsParser(args, arguments)
//    return new JvmResolver(
//      id = id,
//      name = name,
//      description = description,
//      className = interfaceClass,
//      methodName = methodName,
//      arguments = args,
//      decode = decode)
//  }
//
//  /**
//    * 解析为dubbo调用
//    */
//  def dubboParser(recipient: ResolverRecipient): TraphResolver = {
//    val id = recipient.getId
//    val name = recipient.getName
//    val description = recipient.getDescription
//    val interfaceClass = recipient.getInterfaceClass
//    val methodName = recipient.getMethod
//    val version = recipient.getVersion
//    val arguments = recipient.getArguments
//    val decode: String = recipient.getDecode
//
//    val args: List[ResolverArgument] = List[ResolverArgument]()
//    argumentsParser(args, arguments)
//
//    val attachmentArgs: List[ResolverArgument] = List[ResolverArgument]()
//    val attachments = recipient.getAttachments.asInstanceOf[Map[String, Any]]
//    mapParser(attachmentArgs, attachments)
//
//    return new DubboResolver(
//      id = id,
//      name = name,
//      description = description,
//      interfaceClass = interfaceClass,
//      method = methodName,
//      arguments = args,
//      attachments = attachmentArgs,
//      version = version,
//      decode = decode)
//  }
//
//  /**
//    * 解析为http请求
//    */
//  def httpParser(recipient: ResolverRecipient): TraphResolver = {
//    val id = recipient.getId
//    val name = recipient.getName
//    val description = recipient.getDescription
//    val interfaceClass = recipient.getInterfaceClass
//    val methodName = recipient.getMethod
//    val version = recipient.getVersion
//    val arguments = recipient.getArguments
//    val decode: String = recipient.getDecode
//
//    val host = recipient.getHost
//    val path = recipient.getUri
//    val headers = recipient.getHeaders
//    val httpMethod = recipient.getMethod
//    val body = recipient.getBody
//
//    //body参数定义
//    var bodyArg: ResolverArgument = null
//    if (body != null) {
//      bodyArg = new ResolverArgument(
//        name = "body",
//        clazz = classOf[String],
//        dynamic = JavaArgumentHelper.isDynamic(body)
//        ,
//        value = body
//      )
//    }
//
//    val args = List[ResolverArgument]()
//    argumentsParser(args, arguments)
//
//    val headerList = List[ResolverArgument]()
//    mapParser(headerList, headers)
//
//    return new HttpResolver(
//      id = id,
//      name = name,
//      description = description,
//      host = host,
//      path = path,
//      httpMethod = httpMethod,
//      headers = headerList,
//      arguments = args,
//      body = bodyArg,
//      decode = decode
//    )
//  }
//
//  /**
//    * 把map参数解析为resolver argument
//    */
//  def mapParser(list: List[ResolverArgument], map: Map[String, String]) {
//    if (map == null) {
//      return
//    }
//    map.forEach {
//      val name: String = it.key
//      val value: Any ? = it.value
//      val dynamic: Boolean = JavaArgumentHelper.isDynamic(value)
//
//      val arg = new ResolverArgument(
//        name = name,
//        clazz = String ::
//      class.java
//      ,
//      dynamic = dynamic
//      ,
//      value = value
//      )
//      list.add(arg)
//    }
//  }
//
//  /**
//    * arguments 字段解析
//    */
//  def argumentsParser(args: List[ResolverArgument], arguments: List[Map[String, Any]]) {
//    if (arguments == null) {
//      return
//    }
//
//    /*
//    arguments 格式如下
//    arguments:
//      - appVersion:
//          class: string
//          value: 0.2
//    */
//    for (argument in arguments) {
//      var argName = argument.keys.first()
//      if ((argument[argName] as ? MutableMap <*, *>) != null) {
//        val map: MutableMap <*
//        , *> = argument[argName] as MutableMap <*
//        , *>
//        val clazz = map["class"].toString()
//        val value = map["value"]
//        val type = JavaArgumentHelper.getType(clazz)
//        val dynamic = JavaArgumentHelper.isDynamic(value)
//        if (JavaArgumentHelper.isContext(
//        type) )
//        {
//          argName = "_context"
//        }
//        val r = new ResolverArgument(name = argName, clazz =
//        type, dynamic = dynamic
//        , value = value
//        )
//        args.add(r)
//      }
//    }
//  }
//}
