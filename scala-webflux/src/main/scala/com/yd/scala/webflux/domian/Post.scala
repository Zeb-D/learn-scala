package com.yd.scala.webflux.domian

import org.springframework.data.annotation.Id

/**
  * @author created by Zeb-D on 2019-06-10 15:05
  */
class Post {
  @Id
  private var id: String = null
  private var name: String = null

  def this(id: String, name: String) {
    this()
    this.name = name
    this.id = id
  }

  def getId(): String = id

  def setId(id: String) = {
    this.id = id
  }

  def getName(): String = name

  def setName(name: String) = {
    this.name = name
  }
}
