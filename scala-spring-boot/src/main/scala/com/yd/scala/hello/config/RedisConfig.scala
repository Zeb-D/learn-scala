package com.yd.scala.hello.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.{GenericJackson2JsonRedisSerializer, StringRedisSerializer}
import redis.clients.jedis.JedisPoolConfig

/**
  * @author created by Zeb灬D on 2019-12-24 11:04
  */
@Configuration
class RedisConfig {
  private var port = 6379
  private var hostName = "127.0.0.1"
  private var passWord = "yd_redis"

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
    redisTemplate.setKeySerializer(new StringRedisSerializer()) //默认jdk序列化会出现
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer())
    redisTemplate
  }

}
