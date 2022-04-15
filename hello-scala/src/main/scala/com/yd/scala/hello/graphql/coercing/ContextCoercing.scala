package com.yd.scala.hello.graphql.coercing

import graphql.schema.Coercing

/**
  * @author created by Zeb灬D on 2021-09-16 10:46
  */
class ContextCoercing extends Coercing[Any, Any] {
  override def serialize(o: Any): Any = {
    return o;
  }

  override def parseValue(o: Any): Any = {
    return o;
  }

  override def parseLiteral(o: Any): Any = {
    return o;
  }
}
