package com.yd.scala.hello.limit

import java.util

import org.apache.hadoop.hbase.util.{Bytes, MD5Hash}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.RedisScript

/**
  * 我来使用redis 的setbit 命令来实现个bloom filter
  *
  * @author created by Zeb灬D on 2021-02-02 11:44
  */
object RedisBloomFilter {
  // ARGV:偏移量offset数组  KYES[1]: setbit操作的key  全部设置为1
  val BLOOM_FILTER_INSERT_SCRIPT: String = "for _, offset in ipairs(ARGV) do\n " +
    "redis.call(\"setbit\", KEYS[1], offset, 1)\n " +
    "end\n return true";

  // ARGV:偏移量offset数组  KYES[1]: setbit操作的key  检查是否全部为1
  val BLOOM_FILTER_QUERY_SCRIPT: String = "for _, offset in ipairs(ARGV) do\n " +
    "if tonumber(redis.call(\"getbit\", KEYS[1], offset)) == 0 then\n return false\n " +
    "end\n    " +
    "end\n    " +
    "return true";

  /**
    * 通过bloom filter 判断在不在
    *
    * @param key bloom filter key
    * @param arr 对应bit操作，偏移量offset数组
    * @return
    */
  def check(key: String, arr: Array[Int], redisTemplate: RedisTemplate[String, Any]): Boolean = {
    val keys: util.List[String] = new util.ArrayList[String]
    keys.add(key)
    val a = cast(arr)

    val execute = redisTemplate.execute(RedisScript.of[Long](BLOOM_FILTER_QUERY_SCRIPT, classOf[Long]), keys, a: _*)
    execute.asInstanceOf[Long] > 0
  }

  def cast(arr: Array[Int]): Array[java.lang.Integer] = {
    val a = new Array[java.lang.Integer](arr.length)
    for (i <- 0 until arr.length) {
      a.update(i, arr(i).asInstanceOf[java.lang.Integer])
    }
    a
  }

  /**
    * 设置bloom filter 一些值
    *
    * @param key bloom filter key
    * @param arr 对应bit操作，偏移量offset数组
    * @return
    */
  def set(key: String, arr: Array[Int], redisTemplate: RedisTemplate[String, Any]): Boolean = {
    val keys: util.List[String] = new util.ArrayList[String]
    keys.add(key)
    val a = cast(arr)

    val execute = redisTemplate.execute(RedisScript.of[Long](BLOOM_FILTER_INSERT_SCRIPT, classOf[Long]), keys, a: _*)
    execute.asInstanceOf[Long] > 0
  }

  //k 次散列计算出 k 个位点：代表着对应位点要为1
  def getLocations(str: String, k: Int, bits: Int): Array[Int] = {
    var s = str
    val locations: Array[Int] = new Array[Int](k)

    //k 个散列
    for (i <- 0 until k) {
      s = str.concat(String.valueOf(i))
      // 哈希计算,使用的是"MurmurHash3"算法,并每次追加一个固定的i字节进行计算
      val hashValue: Int = MD5Hash.getMD5AsHex(Bytes.toBytes(s)).hashCode
      // 取下标offset
      locations.update(i, Math.abs(hashValue % bits))
    }
    locations
  }


  def TestArgs(): Unit = {
    val limit: Integer = 1
    val time: java.lang.Long = 60L
    Args.test1(time)
    val args = Array(limit, time)
    Args.test(args: _*)
  }
}
