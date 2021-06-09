package com.yd.scala.hello.hbase.service;


import com.yd.scala.hello.hbase.ConversionUtils;
import com.yd.scala.hello.hbase.HBaseClientException;
import com.yd.scala.hello.hbase.HBaseConfigBuilder;
import com.yd.scala.hello.hbase.domian.ColumnFamilyValue;
import com.yd.scala.hello.hbase.domian.ColumnValue;
import com.yd.scala.hello.hbase.domian.RowValue;
import com.yd.scala.hello.hbase.domian.TableDescription;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.shaded.org.apache.commons.io.IOUtils;
import org.apache.hadoop.hbase.shaded.org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.apache.hadoop.hbase.util.MD5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class HBaseClientImpl implements HBaseClient {
    private static final Logger LOG = LoggerFactory.getLogger(HBaseClientImpl.class);
    private static final int DEFAULT_PAGE_SIZE = 20;
    private Connection connection;

    /**
     * init.
     *
     * @param zk multi zk split with ','.
     */
    public HBaseClientImpl(String zk) {
        this(buildConfiguration(zk));
    }

    public HBaseClientImpl(Configuration config) {
        // TODO: add hbase-site.xml resource.

        try {
            connection = ConnectionFactory.createConnection(config);
            addShutdownHook(connection);
            LOG.info("HConnection Create Success. Config:{}", config.toString());
        } catch (Throwable t) {
            LOG.error("Create HConnection Error:{}", t);
            throw new HBaseClientException(t.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public List<TableDescription> listTables() throws Throwable {
        List<TableDescription> result = null;

        try (Admin admin = connection.getAdmin()) {
            HTableDescriptor[] tables = admin.listTables();
            Stream<HTableDescriptor> tableDescriptorsStream = Arrays.stream(tables);
            result = tableDescriptorsStream.map(ConversionUtils::constructTableDescription)
                    .collect(Collectors.toList());
        }

        return result;
    }

    @Override
    public TableDescription getTableInfo(String tableName) throws Throwable {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }

        TableDescription result = null;

        try (Admin admin = connection.getAdmin()) {
            HTableDescriptor tableDesc = admin.getTableDescriptor(TableName.valueOf(tableName));
            result = ConversionUtils.constructTableDescription(tableDesc);
        }

        return result;
    }

    @Override
    public boolean createTable(String tableName, String familyName) throws Throwable {
        HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor hTableDescriptor = new HColumnDescriptor(familyName);
        tableDescriptor.addFamily(hTableDescriptor);
        try (Admin admin = connection.getAdmin()) {
            if (!admin.tableExists(TableName.valueOf(tableName))){
                admin.createTable(tableDescriptor);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<RowValue> head(String tableName, boolean reverse) throws Throwable {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }

        List<RowValue> result = new ArrayList<>();

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Scan scan = new Scan();
            scan.setReversed(reverse);
            scan.setFilter(new PageFilter(DEFAULT_PAGE_SIZE));
            try (ResultScanner rs = table.getScanner(scan)) {
                for (Result r = rs.next(); r != null; r = rs.next()) {
                    result.add(ConversionUtils.constructRowValue(r));
                }
            }
        }

        return result;
    }

    @Override
    public void putRow(String tableName, RowValue row) throws Throwable {
        if (StringUtils.isEmpty(tableName) || Objects.isNull(row)) {
            return;
        }

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Put p = new Put(row.getRowKey());
            for (ColumnFamilyValue family : row.getColumnFamilies()) {
                for (ColumnValue column : family.getColumnValues()) {
                    p.addColumn(Bytes.toBytes(family.getFamilyName()), Bytes.toBytes(column.getColumn()), column.getValue());
                }
            }
            table.put(p);
        }
    }

    @Override
    public void putRow(String tableName, List<RowValue> rows) throws Throwable {
        if (StringUtils.isEmpty(tableName) || CollectionUtils.isEmpty(rows)) {
            return;
        }

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            List<Put> putList = new ArrayList<>();
            for (RowValue row : rows) {
                Put p = new Put(row.getRowKey());
                for (ColumnFamilyValue family : row.getColumnFamilies()) {
                    for (ColumnValue column : family.getColumnValues()) {
                        p.addColumn(Bytes.toBytes(family.getFamilyName()), Bytes.toBytes(column.getColumn()), column.getValue());
                    }
                }
                putList.add(p);
            }
            table.put(putList);
        }
    }

    @Override
    public void deleteRow(String tableName, RowValue row) throws Throwable {
        if (StringUtils.isEmpty(tableName) || Objects.isNull(row)) {
            return;
        }

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Delete d = new Delete(row.getRowKey());
            for (ColumnFamilyValue family : row.getColumnFamilies()) {
                for (ColumnValue column : family.getColumnValues()) {
                    d.addColumn(Bytes.toBytes(family.getFamilyName()), Bytes.toBytes(column.getColumn()));
                }
            }
            table.delete(d);
        }
    }

    @Override
    public void deleteRow(String tableName, List<RowValue> rows) throws Throwable {
        if (StringUtils.isEmpty(tableName) || CollectionUtils.isEmpty(rows)) {
            return;
        }

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            List<Delete> deleteList = new ArrayList<>();
            for (RowValue row : rows) {
                Delete d = new Delete(row.getRowKey());
                for (ColumnFamilyValue family : row.getColumnFamilies()) {
                    for (ColumnValue column : family.getColumnValues()) {
                        d.addColumn(Bytes.toBytes(family.getFamilyName()), Bytes.toBytes(column.getColumn()));
                    }
                }
                deleteList.add(d);
            }
            table.delete(deleteList);
        }
    }

    @Override
    public void appendRow(String tableName, RowValue row) throws IOException {
        if (StringUtils.isEmpty(tableName) || Objects.isNull(row)) {
            return;
        }

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Append append = new Append(row.getRowKey());
            for (ColumnFamilyValue family : row.getColumnFamilies()) {
                for (ColumnValue column : family.getColumnValues()) {
                    append.add(Bytes.toBytes(family.getFamilyName()), Bytes.toBytes(column.getColumn()), column.getValue());
                }
            }
            table.append(append);
        }
    }

    @Override
    public RowValue getRow(String tableName, String rowKey) throws Throwable {
        if (StringUtils.isEmpty(tableName) || StringUtils.isEmpty(rowKey)) {
            return null;
        }

        Result r = null;

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Get get = new Get(Bytes.toBytes(rowKey));
            r = table.get(get);
        }

        return ConversionUtils.constructRowValue(r);
    }

    @Override
    public RowValue getRow(String tableName, RowValue row) throws Throwable {
        if (StringUtils.isEmpty(tableName) || Objects.isNull(row)) {
            return null;
        }
        Result r = null;

        try (Table table = connection.getTable(TableName.valueOf(tableName))) {
            Get get = new Get(row.getRowKey());
            for (ColumnFamilyValue family : row.getColumnFamilies()) {
                for (ColumnValue column : family.getColumnValues()) {
                    get.addColumn(Bytes.toBytes(family.getFamilyName()), Bytes.toBytes(column.getColumn()));
                }
            }
            r = table.get(get);
        }

        return ConversionUtils.constructRowValue(r);
    }

    @Override
    public List<RowValue> query(String tableName, Scan scan) throws Throwable {
        if (StringUtils.isEmpty(tableName) || Objects.isNull(scan)) {
            return null;
        }

        List<RowValue> records = new ArrayList<>();
        ResultScanner scanner = null;
        Table table = null;
        try {
            table = connection.getTable(TableName.valueOf(tableName));
            scanner = table.getScanner(scan);
            for (Result result : scanner) {
                records.add(ConversionUtils.constructRowValue(result));
            }
        } finally {
            IOUtils.closeQuietly(table);
            IOUtils.closeQuietly(scanner);
        }

        return records;
    }

    @Override
    public byte[] buildRowKey(String bizId, String[] dims) {
        if (StringUtils.isEmpty(bizId)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String keyPrefix = MD5Hash.getMD5AsHex(Bytes.toBytes(bizId)).substring(0, 8);
        sb.append(keyPrefix).append(new StringBuilder(bizId).reverse());
        for (String dim : dims) {
            if (!StringUtils.isEmpty(dim)) {
                sb.append("_").append(dim);
            }
        }
        return Bytes.toBytes(sb.toString());
    }

    private static Configuration buildConfiguration(String zk) {
        Configuration config = HBaseConfigBuilder.buildDefaultConfig();
        config.set(HBaseConfigBuilder.ZK, zk);

        return config;
    }

    private static void addShutdownHook(Connection connection) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                LOG.info("HBaseClient Shutdown ...");
                if (connection != null) {
                    connection.close();
                }
                LOG.info("HBaseClient Shutdown Completed.");
            } catch (Throwable t) {
                LOG.error("HBaseClient Shutdown Error:{}", t);
            }
        }));
    }
}