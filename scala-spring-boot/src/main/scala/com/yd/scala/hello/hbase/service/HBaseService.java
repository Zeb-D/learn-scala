package com.yd.scala.hello.hbase.service;

import com.yd.scala.hello.hbase.HBaseClientException;
import com.yd.scala.hello.hbase.domian.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class HBaseService implements IHBaseService {
    public static final String TABLE_NAME = "test:yd_hbase";

    private final Logger logger = LoggerFactory.getLogger(HBaseService.class);

    @Resource
    private HBaseClient hBaseClient;

    @Override
    public List<HBaseColumnFamilyValue> queryByRowKey(String rowKey) {
        if (StringUtils.isEmpty(rowKey)) {
            return null;
        }
        RowValue rowValue;
        try {
            rowValue = hBaseClient.getRow(TABLE_NAME, rowKey);
        } catch (Throwable t) {
            logger.error("queryByRowKey error, rowKey:{}, error:{}", rowKey, t);
            throw new HBaseClientException(t);
        }
        return dataHandler(rowValue);
    }

    @Override
    public void insertData(HBaseColumnFamilyValue hBaseColumnFamilyValue, String rowKey) {
        if (null == hBaseColumnFamilyValue || StringUtils.isEmpty(hBaseColumnFamilyValue.getFamilyName())
                || CollectionUtils.isEmpty(hBaseColumnFamilyValue.getRowValues())) {
            return;
        }
        List<ColumnValue> columnValues = new ArrayList<>();
        hBaseColumnFamilyValue.getRowValues()
                .forEach(it -> columnValues.add(new ColumnValue(it.getRowName(), it.getRowValue().getBytes())));
        List<ColumnFamilyValue> columnFamilies = new ArrayList<>();
        columnFamilies.add(new ColumnFamilyValue(hBaseColumnFamilyValue.getFamilyName(), columnValues));
        RowValue rowValue = new RowValue(rowKey.getBytes(), columnFamilies);
        try {
            hBaseClient.putRow(TABLE_NAME, rowValue);
        } catch (Throwable t) {
            logger.error("HbaseService insertData error, e", t);
            throw new HBaseClientException(t);
        }
    }

    @Override
    public byte[] buildRowKey(String bizId, String... dims) {
        try {
            return hBaseClient.buildRowKey(bizId, dims);
        } catch (Throwable t) {
            logger.error("HbaseService buildRowKey error, e", t);
            throw new HBaseClientException(t);
        }
    }

    private List<HBaseColumnFamilyValue> dataHandler(RowValue rowValue) {
        if (null == rowValue) {
            return null;
        }
        List<HBaseColumnFamilyValue> hBaseColumnFamilyValues = new ArrayList<>();
        for (ColumnFamilyValue columnFamilyValue : rowValue.getColumnFamilies()) {
            HBaseColumnFamilyValue hBaseColumnFamilyValue = new HBaseColumnFamilyValue();
            hBaseColumnFamilyValue.setFamilyName(columnFamilyValue.getFamilyName());
            List<HBaseRowValue> hBaseRowValues = new ArrayList<>();
            for (ColumnValue columnValue : columnFamilyValue.getColumnValues()) {
                hBaseRowValues.add(new HBaseRowValue(columnValue.getColumn(), new String(columnValue.getValue(), StandardCharsets.UTF_8)));
            }
            hBaseColumnFamilyValue.setRowValues(hBaseRowValues);
            hBaseColumnFamilyValues.add(hBaseColumnFamilyValue);
        }
        return hBaseColumnFamilyValues;
    }
}
