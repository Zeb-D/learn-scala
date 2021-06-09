package com.yd.scala.hello.hbase.domian

import java.util

/**
  * @author created by Zeb灬D on 2021-02-26 21:32
  */
class ColumnFamilyValue(familyName: String, columnValues: util.List[ColumnValue]) {

  def getFamilyName: String = familyName

  def getColumnValues: util.List[ColumnValue] = columnValues

  override def toString: String = "ColumnFamilyValue{" + "familyName='" + familyName + '\'' + ", columnValues=" + columnValues + '}'
}
