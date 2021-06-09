package com.yd.scala.hello.hbase.domian

/**
  * @author created by Zeb灬D on 2021-02-26 21:32
  */
class ColumnValue(column: String, value: Array[Byte]) {

  def getValue: Array[Byte] = value

  def getColumn: String = column

  override def toString: String = "ColumnValue{" + "column='" + column + '\'' + ", value='" + value + '\'' + '}'
}
