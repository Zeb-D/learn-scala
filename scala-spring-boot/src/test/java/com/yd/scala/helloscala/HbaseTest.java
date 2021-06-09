package com.yd.scala.helloscala;

import com.yd.scala.hello.hbase.domian.ColumnFamilyValue;
import com.yd.scala.hello.hbase.domian.ColumnValue;
import com.yd.scala.hello.hbase.domian.RowValue;
import com.yd.scala.hello.hbase.domian.TableDescription;
import com.yd.scala.hello.hbase.service.HBaseClient;
import com.yd.scala.hello.hbase.service.IHBaseService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.shaded.org.mortbay.util.ajax.JSON;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author created by Zeb灬D on 2021-02-27 14:36
 */
public class HbaseTest extends BaseTest {
    @Resource
    private HBaseClient hBaseClient;
    @Resource
    private IHBaseService hBaseService;

    @Test
    public void testHbase() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "127.0.0.1");
        conf.set("hbase.zookeeper.property.clientPort", "9181");
        Connection conn = ConnectionFactory.createConnection(conf);

        System.out.println(conn);
        Admin admin = conn.getAdmin();
        System.out.println(admin.tableExists(TableName.valueOf("test:user")));
        System.out.println(JSON.toString(admin.listTables()));
    }

    @Test
    public void testHbaseClient() throws Throwable {
        System.out.println(hBaseClient);
        List<TableDescription> tableDescriptionList = hBaseClient.listTables();
        System.out.println(tableDescriptionList);

        //创建表
        String tableName = "yd_test";
        String familyName = "yt";
        boolean s = hBaseClient.createTable(tableName, "yt");
        System.out.println(s);

        //查看表定义
        TableDescription tableDescription = hBaseClient.getTableInfo(tableName);
        System.out.println(tableDescription);

        //加字段
        String uid = "yd_uid";
        String devId = "yd_device";
        Long endTime = 123456789L;
        byte[] rowKeys = hBaseClient.buildRowKey(uid, new String[]{devId, endTime + ""});
        String rowKey = new String(rowKeys, StandardCharsets.UTF_8);

        List<ColumnFamilyValue> columnFamilies = new ArrayList<>();
        //一个个字段，放到具体的列族
        List<ColumnValue> columnValues = new ArrayList<>();
        columnValues.add(new ColumnValue("op1", "{}".getBytes()));
        columnValues.add(new ColumnValue("name", "yd".getBytes()));
        columnValues.add(new ColumnValue("sex", "male".getBytes()));
        //yt 列族 挂这3个属性
        columnFamilies.add(new ColumnFamilyValue(familyName, columnValues));
        RowValue rowValue = new RowValue(rowKey.getBytes(), columnFamilies);
        //插入数据
        hBaseClient.putRow(tableName, rowValue);


        //根据主键获取对象，rowKey要唯一
        rowValue = hBaseClient.getRow(tableName, rowKey);
        System.out.println(new String(rowValue.getRowKey()));
        List<String> columnNames = rowValue.getColumnFamilies()
                .stream().flatMap(it -> it.getColumnValues().stream().map(ColumnValue::getColumn))
                .collect(Collectors.toList());
        System.out.println(columnNames);

        //根据主键前置动作
        rowKeys = hBaseClient.buildRowKey(uid, new String[]{devId});
        rowKey = new String(rowKeys, StandardCharsets.UTF_8);
        rowValue = hBaseClient.getRow(tableName, rowKey);
        System.out.println(new String(rowValue.getRowKey()));
        columnNames = rowValue.getColumnFamilies()
                .stream().flatMap(it -> it.getColumnValues().stream().map(ColumnValue::getColumn))
                .collect(Collectors.toList());
        System.out.println(columnNames);

    }
}
