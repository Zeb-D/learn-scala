package com.yd.scala.hello.hbase;

import com.yd.scala.hello.hbase.domian.ColumnFamilyValue;
import com.yd.scala.hello.hbase.domian.ColumnValue;
import com.yd.scala.hello.hbase.domian.RowValue;
import com.yd.scala.hello.hbase.domian.TableDescription;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Result;

import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;
import java.util.stream.Collectors;

public class ConversionUtils {

    public static TableDescription constructTableDescription(HTableDescriptor htd) {
        TableDescription result = null;

        if (htd != null) {
            String name = htd.getNameAsString();
            Collection<HColumnDescriptor> families = htd.getFamilies();

            List<String> familiesNames = families.stream()
                    .map(HColumnDescriptor::getNameAsString)
                    .collect(Collectors.toList());

            result = new TableDescription(name, familiesNames);
        }

        return result;
    }

    public static RowValue constructRowValue(Result r) {
        RowValue result = null;

        if (r != null && !r.isEmpty()) {
            byte[] rowKey = r.getRow();

            // Map<family,Map<qualifier,value>>
            NavigableMap<byte[], NavigableMap<byte[], byte[]>> valueMap = r.getNoVersionMap();

            List<ColumnFamilyValue> families = valueMap.entrySet().stream()
                    .map(b -> new ColumnFamilyValue(new String(b.getKey()), constructColumnValues(b.getValue())))
                    .collect(Collectors.toList());

            result = new RowValue(rowKey, families);
        }

        return result;
    }

    private static List<ColumnValue> constructColumnValues(NavigableMap<byte[], byte[]> map) {
        List<ColumnValue> result = null;
        result = map.entrySet().stream()
                .map(i -> new ColumnValue(new String(i.getKey()), i.getValue()))
                .collect(Collectors.toList());
        return result;
    }

}
