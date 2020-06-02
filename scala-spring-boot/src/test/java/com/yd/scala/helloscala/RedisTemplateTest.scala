package com.yd.scala.helloscala

import javax.annotation.Resource
import org.junit.Test
import org.springframework.data.redis.core.RedisTemplate

/**
  * @author created by Zeb灬D on 2019-12-24 11:01
  */
class RedisTemplateTest extends BaseTest {
  @Resource
  var redisTemplate: RedisTemplate[String, Any] = _

  @Test
  def TestRedis() {
    println(redisTemplate)
  }

  @Test
  def TestRedisWrite() {
    val user = new User(age = 11, name = "Yd")
    val a: Int = 111
    redisTemplate.opsForValue().set("test:yd", user)
    println(redisTemplate.opsForValue().get("test:yd"))
  }

  @Test
  def TestUser(): Unit = {
    val u: UserService = new UserImpl
    val (name, err) = u.SayHello("Yd")
    println(s"$name,$err")
    println(u.SayHello("Yd"))
  }

  @Test
  def TestP(): Unit = {
    val p: Person = new User("111", 222)
    println(p.name)
    println(p.isInstanceOf[User])
  }

  @Test
  def TestList() {
    val fruits = Array("apple", "banana", "orange")
    for (i <- 0 until fruits.size) println(s"$i is ${fruits(i)}")
    for ((elem, count) <- fruits.zipWithIndex) { // 在zip中使用Stream是一种生存计数器的方法。
      println(s"$count is $elem")
    }
    println(fruits.reduceLeft(_ + _))
    val immutableMap = Map("Jim" -> 22, "yxj" -> 32) // 不可变Map
    // 创建可变的map
    val ages = scala.collection.mutable.Map("jim" -> 20, "link" -> 33)
    ages("jim") = 30 // 可以正常修改
    val maps = immutableMap + ("yxj" -> 33)
    println(ages)
    for ((key, value) <- ages) { // map的遍历，遍历所有的
      println(key + ":" + value)
    }
    for (key <- ages.keySet) { // 遍历key
      println("key=" + key)
    }
  }

  @Test
  def TestRF(): Unit = {
    val clazz = Class.forName("com.yd.scala.helloscala.UserImpl") //构造一个需要反射类的对象
    val value = clazz.getDeclaredMethod(s"SayHello", classOf[String]) //并得到该函数入参的数据类型,如有多个入参,要声明多个classOf
      .invoke(clazz.newInstance(), "abc") //激活该函数,传入入参
    println(value)
    val (aa, bb) = value
    println(aa.asInstanceOf[String], "->", bb)

//    val ru = scala.reflect.runtime.universe
//    val m = ru.runtimeMirror(getClass.getClassLoader)
//    val classPerson = ru.typeOf[Person].typeSymbol.asClass
//    val cm = m.reflectClass(classPerson)
//    val ctor = ru.typeOf[Person].declaration(ru.nme.CONSTRUCTOR).asMethod
//    val ctorm = cm.reflectConstructor(ctor)
//    val p = ctorm("Mike")
  }
}
class Person {
  var name: String = ""

  def this(name: String) {
    this()
    this.name = name
  }
}

class UserImpl extends UserService {
  override def SayHello(name: String): (String, Int) = {
    return (s"hello $name", 112);
  }
}

@SerialVersionUID(1)
class User(name: String, age: Int) extends Person(name) with Serializable {
  override def toString = s"{name:$name,age:$age}"
}


trait UserService {
  def SayHello(name: String): (String, Int)
}




