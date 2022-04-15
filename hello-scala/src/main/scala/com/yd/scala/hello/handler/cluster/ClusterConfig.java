package com.yd.scala.hello.handler.cluster;


import com.alibaba.fastjson.JSON;
import com.yd.scala.hello.handler.path.PathConfig;

import java.util.List;


public class ClusterConfig {
    private String id;
    private String protocol;
    private List<PathConfig> pathConfigs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public List<PathConfig> getPathConfigs() {
        return pathConfigs;
    }

    public void setPathConfigs(List<PathConfig> pathConfigs) {
        this.pathConfigs = pathConfigs;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
