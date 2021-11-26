package com.yd.test.crypto

import java.io.{File, IOException, UnsupportedEncodingException}

import com.yd.scala.hello.crypto.config.LocalConfigLoader
import com.yd.scala.hello.crypto.encryption.{EncryptionProvider, IEncryptionProvider}
import com.yd.scala.hello.crypto.handler.EncryptedBigDecimalTypeHandler
import org.apache.commons.codec.binary.Base64
import org.apache.commons.io.FileUtils
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

/**
  * @author created by Zeb灬D on 2021-03-09 16:19
  */
@RunWith(classOf[SpringRunner])
class CryptoTest {
  var provider: IEncryptionProvider = new EncryptionProvider(new LocalConfigLoader, true)

  @Test
  def TestCryptoString() {
    val plainText = "1234567890bcdef1"
    val encrypt = provider.encrypt(plainText.getBytes)

    try {
      val decrypt = provider.decrypt(encrypt)
      val text = new String(decrypt, "utf-8")
      System.out.println(text)
    } catch {
      case var6: RuntimeException =>
        var6.printStackTrace()
      case var7: UnsupportedEncodingException =>
        var7.printStackTrace()
    }
  }

  @Test
  def TestCryptoFile(): Unit = {
    try {
      val startEncryptTime = System.currentTimeMillis
      val imageFile = new File("/Users/xx/Documents/cropped.jpg")
      val bytes = FileUtils.readFileToByteArray(imageFile)
      val encryptBytes = provider.encrypt(bytes)
      System.out.println("加密耗时:" + (System.currentTimeMillis - startEncryptTime) + "ms")
      val startDecryptTime = System.currentTimeMillis
      val encryptedFile = new File("/Users/xx/Documents/1.jpg")
      FileUtils.writeByteArrayToFile(encryptedFile, encryptBytes)
      val decryptBytes = provider.decrypt(FileUtils.readFileToByteArray(encryptedFile))
      val decryptedFile = new File("/Users/xx/Documents/2.jpg")
      FileUtils.writeByteArrayToFile(decryptedFile, decryptBytes)
      System.out.println("解密耗时:" + (System.currentTimeMillis - startDecryptTime) + "ms")
    } catch {
      case var11: IOException =>
        var11.printStackTrace()
      case var12: RuntimeException =>
        var12.printStackTrace()
    }
  }

  @Test
  def TestCryptoText(): Unit = {
    val plainText = "1234567890bcdef1"
    System.err.println("plain:" + plainText)
    val cipherBytes = provider.encrypt(plainText.getBytes)
    val cipherString = Base64.encodeBase64String(cipherBytes)
    System.err.println("cipher String:" + cipherString)
    var decrypted: Array[Byte] = null
    try {
      decrypted = provider.decrypt(cipherBytes)
    }
    catch {
      case var6: RuntimeException =>

    }
    if (decrypted != null) System.err.println("decrypted:" + new String(decrypted))
  }

  @Test
  def TestHandler() {
    val h  =new EncryptedBigDecimalTypeHandler
    println(h.getRawType)
  }
}
