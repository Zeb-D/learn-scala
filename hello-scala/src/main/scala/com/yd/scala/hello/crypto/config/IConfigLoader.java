package com.yd.scala.hello.crypto.config;

import java.util.Map;

public interface IConfigLoader {
    Map<Integer, KeyConfig> getConfigs(String var1);

    String getDefaultKey();

    //全球使用到的key，对应值都一样
    String getRegionSyncKey();
}