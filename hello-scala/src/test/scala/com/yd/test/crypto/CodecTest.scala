package com.yd.test.crypto

import java.nio.charset.Charset

import org.apache.commons.codec.binary.{Base64, Hex}
import org.junit.Test

/**
  * @author created by Zeb灬D on 2021-11-26 17:47
  */
class CodecTest {

  @Test
  def Test(): Unit = {
    val s = "abc"
    val ebs = Base64.encodeBase64(s.getBytes(Charset.defaultCharset))
    val encodeHexString = Hex.encodeHexString(ebs)
    //解码
    val dbs = Hex.decodeHex(encodeHexString)
    println(ebs.equals(dbs))
    val bs = Base64.decodeBase64(dbs)
    println(new String(bs).equals(s))
  }
}
