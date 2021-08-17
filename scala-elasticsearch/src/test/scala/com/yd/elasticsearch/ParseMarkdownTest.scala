package com.yd.elasticsearch

import com.yd.scala.elasticsearch.markdown.ParseMarkdown
import org.junit.Test

/**
  * @author created by Zeb灬D on 2020-06-20 16:55
  */
@Test
class ParseMarkdownTest {

  @Test
  def parse(): Unit = {
    val path = "/Users/zouyongdong/IdeaProjects/tuya/tuya-openapi-doc/openapi/2-user-intergration.md"
    val text = ParseMarkdown.parseText(path)
    val doc =  ParseMarkdown.parseDoc(text)
    println(doc)
  }

}
