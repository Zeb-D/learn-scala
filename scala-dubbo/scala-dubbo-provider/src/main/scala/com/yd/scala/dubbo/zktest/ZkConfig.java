package com.yd.scala.dubbo.zktest;

import org.I0Itec.zkclient.ZkClient;

/**
 * @author created by Zeb灬D on 2021-12-09 10:37
 */
public class ZkConfig {
    public static ZkClient zkClient = new ZkClient("localhost:2181");

    public static void main(String[] args) {
        int count = zkClient.countChildren("/");
        System.out.println(count);
        zkClient.createPersistent(ZKWrite.ZK_PATH);
    }
}
