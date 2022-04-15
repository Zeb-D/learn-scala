package com.yd.test.graphql

import com.yd.scala.hello.graphql.core.ResolverRecipient
import com.yd.scala.hello.graphql.load.ResolverLoader
import org.junit.Test

/**
  * @author created by Zeb灬D on 2021-09-15 16:53
  */
class ResolverLoaderTest {
  @Test
  def TestLoad() = {
    val list: List[ResolverRecipient] = ResolverLoader.load("graphql/resolver")
    println(list)
  }
}
