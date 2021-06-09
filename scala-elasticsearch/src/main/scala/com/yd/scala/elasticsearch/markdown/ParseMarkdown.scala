package com.yd.scala.elasticsearch.markdown

import java.io.File
import java.util

import com.vladsch.flexmark.ext.tables.TablesExtension
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.{Parser, ParserEmulationProfile}
import com.vladsch.flexmark.util.ast.Document
import com.vladsch.flexmark.util.data.MutableDataSet

/**
  * 解析markdown文件
  *
  * @author created by Zeb灬D on 2020-06-20 16:37
  */
class ParseMarkdown {
}

object ParseMarkdown {
  def parseDoc(text: String): Document = {
    val options = new MutableDataSet
    options.setFrom(ParserEmulationProfile.MARKDOWN)
    //enable table parse!
    options.set(Parser.EXTENSIONS, util.Arrays.asList(TablesExtension.create))
    val parser = Parser.builder(options).build
    val doc: Document = parser.parse(text)
    val renderer = HtmlRenderer.builder(options).build
    val html = renderer.render(doc)
    return doc
  }

  def parseText(path: String): String = {
    import java.io.{FileInputStream, FileNotFoundException, IOException, UnsupportedEncodingException}
    val encoding = "UTF-8"
    val file = new File(path)
    val filelength = file.length
    val filecontent = new Array[Byte](filelength.intValue)
    try {
      val in = new FileInputStream(file)
      in.read(filecontent)
      in.close()
      return new String(filecontent, encoding)
    } catch {
      case e: FileNotFoundException =>
        e.printStackTrace()
      case e: IOException =>
        e.printStackTrace()
    }

    return null
  }
}
