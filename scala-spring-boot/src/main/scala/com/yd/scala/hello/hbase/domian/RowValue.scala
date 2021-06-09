package com.yd.scala.hello.hbase.domian

import java.util

/**
  * @author created by Zeb灬D on 2021-02-26 21:35
  */
class RowValue() {
  private var rowKey: Array[Byte] = null
  private var columnFamilies: util.List[ColumnFamilyValue] = null

  def this(rowKey: Array[Byte], columnFamilies: util.List[ColumnFamilyValue]) {
    this()
    this.rowKey = rowKey
    this.columnFamilies = columnFamilies
  }

  def getRowKey: Array[Byte] = rowKey

  def getColumnFamilies: util.List[ColumnFamilyValue] = columnFamilies
}
