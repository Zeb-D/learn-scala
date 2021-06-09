package com.yd.scala.hello.hbase.service

import java.util

import com.yd.scala.hello.hbase.domian.{ColumnFamilyValue, RowValue, TableDescription}
import org.apache.hadoop.hbase.client.{Connection, Scan}

/**
  * @author created by Zeb灬D on 2021-02-26 21:40
  */
trait HBaseClient {
  def getConnection: Connection

  @throws[Throwable]
  def listTables: util.List[TableDescription]

  @throws[Throwable]
  def getTableInfo(tableName: String): TableDescription

  @throws[Throwable]
  def createTable(tableName: String, familyName: String): Boolean

  @throws[Throwable]
  def head(tableName: String, reverse: Boolean): util.List[RowValue]

  @throws[Throwable]
  def putRow(tableName: String, row: RowValue): Unit

  @throws[Throwable]
  def putRow(tableName: String, rows: util.List[RowValue]): Unit

  @throws[Throwable]
  def deleteRow(tableName: String, row: RowValue): Unit

  @throws[Throwable]
  def deleteRow(tableName: String, rows: util.List[RowValue]): Unit

  @throws[Throwable]
  def getRow(tableName: String, rowKey: String): RowValue

  /**
    * 获取指定的列
    *
    * @param tableName
    * @param row
    * @return
    * @throws Throwable
    */
  @throws[Throwable]
  def getRow(tableName: String, row: RowValue): RowValue

  @throws[Throwable]
  def appendRow(tableName: String, row: RowValue): Unit

  /**
    * 生成rowkey，生成算法：md5(bizId).sub(0, 8)_bizId_dim1_dim2
    *
    * @param bizId
    * @param dims 维度值列表，这些维度值会出现在rowkey中，使用'_'分隔
    * @return
    * @throws Throwable
    */
  @throws[Throwable]
  def buildRowKey(bizId: String, dims: Array[String]): Array[Byte]

  @throws[Throwable]
  def query(tableName: String, scan: Scan): util.List[RowValue]
}
