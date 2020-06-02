package com.yd.scala.hello

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import redis.clients.jedis.JedisPoolConfig

/**
  * @author created by Zeb灬D on 2019-12-24 11:04
  */
@Configuration
class RedisConfig {
  private var port = 7000
  private var hostName = "127.0.0.1"
  private var passWord = "redisTest"

  @Bean
  def jedisPoolConfig: JedisPoolConfig = {
    val jedisPoolConfig = new JedisPoolConfig
    jedisPoolConfig.setMaxIdle(10)
    jedisPoolConfig.setMinIdle(0)
    jedisPoolConfig.setMaxTotal(200)
    jedisPoolConfig.setMaxWaitMillis(-1)
    jedisPoolConfig.setTestOnBorrow(true)
    jedisPoolConfig
  }

  @Bean
  def jedisConnectionFactory(jedisPoolConfig: JedisPoolConfig): JedisConnectionFactory = {
    val jedisConnectionFactory = new JedisConnectionFactory
    jedisConnectionFactory.setPoolConfig(jedisPoolConfig)
    jedisConnectionFactory.setHostName(hostName)
    jedisConnectionFactory.setUsePool(true)
    jedisConnectionFactory.setPassword(passWord)
    jedisConnectionFactory.setPort(port)
    jedisConnectionFactory
  }

  @Bean
  def redisTemplate(jedisConnectionFactory: JedisConnectionFactory): RedisTemplate[_, _] = {
    val redisTemplate = new RedisTemplate
    redisTemplate.setConnectionFactory(jedisConnectionFactory)
    redisTemplate
  }

}
