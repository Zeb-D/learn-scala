package com.yd.scala.hello.graphql.provider

import java.math.BigInteger
import java.util.regex.Pattern

import com.yd.scala.hello.graphql.coercing.{AnyCoercing, ContextCoercing}
import com.yd.scala.hello.sdk.SdkContext
import graphql.Scalars
import graphql.schema.GraphQLScalarType

import scala.collection.mutable

/**
  * @author created by Zeb灬D on 2021-09-15 20:38
  */
object JavaArgumentHelper {
  val cacheClass: mutable.Map[String, Class[_]] = mutable.Map()
  val cacheClass2: mutable.Map[Class[_], String] = mutable.Map()
  val scalar: mutable.Map[Class[_], GraphQLScalarType] = mutable.Map()

  cacheClass += ("string" -> classOf[String])
  cacheClass += ("String" -> classOf[String])
  cacheClass += ("boolean" -> Boolean.getClass)
  cacheClass += ("Boolean" -> classOf[Boolean])
  cacheClass += ("byte" -> Byte.getClass)
  cacheClass += ("Byte" -> classOf[Byte])
  cacheClass += ("short" -> Short.getClass)
  cacheClass += ("short" -> classOf[Short])
  cacheClass += ("int" -> Int.getClass)
  cacheClass += ("Integer" -> classOf[Int])
  cacheClass += ("float" -> Float.getClass)
  cacheClass += ("Float" -> classOf[Float])
  cacheClass += ("double" -> Double.getClass)
  cacheClass += ("Double" -> classOf[Double])
  cacheClass += ("long" -> Long.getClass)
  cacheClass += ("Long" -> classOf[Long])
  cacheClass += ("context" -> classOf[SdkContext])
  cacheClass += ("Context" -> classOf[SdkContext])

  val mapType: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Map")
    .description("This is  map scalar")
    .coercing(new AnyCoercing())
    .build();

  val context: GraphQLScalarType = GraphQLScalarType.newScalar()
    .name("Context")
    .description("This is  context scalar")
    .coercing(new ContextCoercing())
    .build();

  scalar += (classOf[SdkContext] -> context)
  scalar += (classOf[Map[_, _]] -> mapType)
  scalar += (classOf[String] -> Scalars.GraphQLString)
  scalar += (classOf[Boolean] -> Scalars.GraphQLBoolean)
  scalar += (classOf[Int] -> Scalars.GraphQLInt)
  scalar += (classOf[Byte] -> Scalars.GraphQLByte)
  scalar += (classOf[Short] -> Scalars.GraphQLShort)
  scalar += (classOf[Long] -> Scalars.GraphQLLong)
  scalar += (classOf[Float] -> Scalars.GraphQLFloat)
  scalar += (classOf[BigInteger] -> Scalars.GraphQLBigInteger)
  scalar += (classOf[Double] -> Scalars.GraphQLFloat)

  def getType(string: String): Class[Any] = {
    val clazz: Class[_] = cacheClass.get(string).orNull
    if (clazz == null) return null
    return clazz.asInstanceOf[Class[Any]]
  }

  def getScalar(clazz: Class[_]): GraphQLScalarType = {
    return scalar.get(clazz).orNull
  }

  def isScalar(clazz: Class[_]): Boolean = {
    return scalar.get(clazz).nonEmpty
  }

  def isDynamic(value: Any): Boolean = {
    if (value == null) return true
    var v: String = ""
    if (value.isInstanceOf[String]) {
      v = value.asInstanceOf[String]
    }
    return Pattern.matches("^\\$\\{.*}$", v)
  }

  def isContext(clazz: Class[_]): Boolean = {
    if (clazz.isAssignableFrom(classOf[SdkContext])) {
      return true
    }
    return false
  }
}
