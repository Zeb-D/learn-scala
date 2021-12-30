package com.yd.scala.dubbo.zktest;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author created by Zeb灬D on 2021-12-09 10:58
 */
public class ZKWatcher {
    public static void main(String[] args) {
        List<String> watchers = ZkConfig.zkClient.watchForChilds(ZKWrite.ZK_PATH);
        System.out.println(JSONObject.toJSONString(watchers));
        ZkConfig.zkClient.subscribeChildChanges(ZKWrite.ZK_PATH, ((parentPath, currentChilds) -> {
            System.out.println("parentPath:"+parentPath+"=currentChilds=>"+JSONObject.toJSONString(currentChilds));
        }));
    }
}
