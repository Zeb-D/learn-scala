package com.yd.scala.hello.hbase.domian

import java.util

/**
  * @author created by Zeb灬D on 2021-02-26 21:38
  */
class TableDescription(tableName: String, columnFamilies: util.List[String]) {

  def this(tableName: String) {
    this(tableName, null)
  }

  def getTableName: String = tableName

  def getColumnFamilies: util.List[String] = columnFamilies

  override def toString: String = "TableDescription{" + "tableName='" + tableName + '\'' + ", columnFamilies=" + columnFamilies + '}'
}
