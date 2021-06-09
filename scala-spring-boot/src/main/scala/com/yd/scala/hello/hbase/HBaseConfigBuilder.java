package com.yd.scala.hello.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

/**
 * @author created by Zeb灬D on 2021-02-26 21:51
 */
public class HBaseConfigBuilder {
    /**
     * hbase client config key.
     */
    public static final String ZK = "hbase.zookeeper.quorum";
    public static final String ZK_SESSION = "zookeeper.session.timeout";
    public static final String ZK_RECOVERY_RETRY = "zookeeper.recovery.retry";
    public static final String CLIENT_RETRY_COUNT = "hbase.client.retries.number";
    public static final String CLIENT_PAUSE = "hbase.client.pause";
    public static final String CLIENT_RPC_TIMEOUT = "hbase.rpc.timeout";
    public static final String CLIENT_OPERATION_TIMEOUT = "hbase.client.operation.timeout";

    private static final String DEFAULT_ZK_SESSION = "30000";
    private static final String DEFAULT_ZK_RECOVERY_RETRY = "1";
    private static final String DEFAULT_CLIENT_RETRY_COUN = "7";
    private static final String DEFAULT_CLIENT_PAUSE = "100";

    public static Configuration buildDefaultConfig() {
        Configuration config = HBaseConfiguration.create();
        config.set(ZK_SESSION, DEFAULT_ZK_SESSION);
        config.set(ZK_RECOVERY_RETRY, DEFAULT_ZK_RECOVERY_RETRY);
        config.set(CLIENT_RETRY_COUNT, DEFAULT_CLIENT_RETRY_COUN);
        config.set(CLIENT_PAUSE, DEFAULT_CLIENT_PAUSE);
        config.set("hbase.zookeeper.property.clientPort", "9181");

        return config;
    }
}
