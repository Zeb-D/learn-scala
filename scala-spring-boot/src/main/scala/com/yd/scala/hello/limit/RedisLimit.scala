package com.yd.scala.hello.limit

import java.util

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.RedisScript

/**
  * @author created by Zeb灬D on 2021-02-02 11:44
  */
object RedisLimit {
  val LIMIT_SCRIPT: String = "local key = KEYS[1]\n" +
    "local limit = tonumber(ARGV[1])\n" +
    "local currentLimit = tonumber(redis.call('get', key) or \"0\")\n" +
    "if currentLimit + 1 > limit then\n" +
    "    return 0\n" +
    "else\n" +
    "    redis.call(\"INCRBY\", key, 1)\n" +
    "    redis.call(\"EXPIRE\", key, ARGV[2])\n" +
    "    return currentLimit + 1\n" +
    "end";

  /**
    * 限流，一段时间内只能创建一定的数量
    *
    * @param key   存放限流 key
    * @param limit 限流的数量
    * @param time  超时时间，key不存在是，设置超时  s为单位
    * @return
    */
  def createLimit(key: String, limit: Int, time: Long, redisTemplate: RedisTemplate[String, Any]): Boolean = {
    val keys: util.List[String] = new util.ArrayList[String]
    keys.add(key)
    val arr = Array(limit.asInstanceOf[java.lang.Integer], time.asInstanceOf[java.lang.Long]) //这样做,scala的基本数据类型到Java的Object会出现范型问题

    val execute = redisTemplate.execute(RedisScript.of[Long](LIMIT_SCRIPT, classOf[Long]), keys, arr: _*)
    execute.asInstanceOf[Long] <= 0
  }

  def TestArgs(): Unit = {
    val limit: Integer = 1
    val time: java.lang.Long = 60L
    Args.test1(time)
    val args = Array(limit, time)
    Args.test(args: _*)
  }
}
