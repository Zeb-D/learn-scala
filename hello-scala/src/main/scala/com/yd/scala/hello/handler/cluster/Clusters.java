package com.yd.scala.hello.handler.cluster;


import com.yd.scala.hello.handler.path.RootPath;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Clusters {
    private final static Map<String, Box> BOX_MAP = new ConcurrentHashMap<>();

    public static void put(String clusterId, ClusterConfig clusterConfig) {

        RootPath rootPath = new RootPath(clusterConfig);
        Box box = new Box();
        box.setClusterConfig(clusterConfig);
        box.setRootPath(rootPath);

        BOX_MAP.put(clusterId, box);
    }

    public static ClusterConfig getClusterConfig(String clusterId) {
        Box box = BOX_MAP.get(clusterId);
        return box == null ? null : box.getClusterConfig();
    }

    public static RootPath getRootPath(String clusterId) {
        Box box = BOX_MAP.get(clusterId);
        return box == null ? null : box.getRootPath();
    }

    public static class Box {
        private ClusterConfig clusterConfig;
        private RootPath rootPath;

        ClusterConfig getClusterConfig() {
            return clusterConfig;
        }

        void setClusterConfig(ClusterConfig clusterConfig) {
            this.clusterConfig = clusterConfig;
        }

        RootPath getRootPath() {
            return rootPath;
        }

        void setRootPath(RootPath rootPath) {
            this.rootPath = rootPath;
        }
    }
}
