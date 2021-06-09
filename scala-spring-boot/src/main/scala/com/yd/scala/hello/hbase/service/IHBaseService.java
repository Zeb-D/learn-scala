package com.yd.scala.hello.hbase.service;


import com.yd.scala.hello.hbase.domian.HBaseColumnFamilyValue;

import java.util.List;

public interface IHBaseService {
    List<HBaseColumnFamilyValue> queryByRowKey(String rowKey);

    void insertData(HBaseColumnFamilyValue hBaseColumnFamilyValue, String rowKey);

    byte[] buildRowKey(String bizId, String... dims);
}
