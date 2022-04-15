package com.yd.scala.hello.handler.path;

import com.yd.scala.hello.handler.cluster.ClusterConfig;
import com.yd.scala.hello.handler.leaf.ElementLeaf;
import com.yd.scala.hello.handler.leaf.FieldLeaf;
import com.yd.scala.hello.handler.leaf.Leaf;
import com.yd.scala.hello.handler.protocol.Protocols;
import com.yd.scala.hello.handler.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.utils.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * path定义：
 * $.name
 * $.[*]
 **/
@Slf4j
public class RootPath extends Path {
    private final ClusterConfig cluster;

    public RootPath(ClusterConfig clusterConfig) {
        super(null, "$");
        this.cluster = clusterConfig;
        if (clusterConfig == null || clusterConfig.getPathConfigs() == null) {
            return;
        }
        List<PathConfig> pathConfigs = clusterConfig.getPathConfigs();
        if (CollectionUtils.isEmpty(pathConfigs)) {
            return;
        }
        //获取相同的参数路径
        List<String> paths = pathConfigs.stream().map(p -> p.getPath()).filter(Objects::nonNull).collect(Collectors.toList());
        String commonPath = CommonUtil.getSamePath(paths);
        Path curPath = this;
        Boolean flag = false;
        int start = 1;
        for (PathConfig pathConfig : clusterConfig.getPathConfigs()) {
            String path = pathConfig.getPath();
            if (StringUtils.isBlank(path)) {
                continue;
            }
            String[] pathArray = path.split("\\.");
            if (pathArray.length < 2 || !"$".equals(pathArray[0])) {
                continue;
            }
            if (StringUtils.isNotBlank(commonPath) && path.contains(commonPath) && flag) {
                start = commonPath.split("\\.").length;
            }
            Path parentPath;
            final int len = pathArray.length - 1;
            for (int i = start; i < len; ++i) {
                String shortName = pathArray[i];
                parentPath = curPath;
                ChildPath childPath = getChildPath(parentPath, isArrayPath(shortName));
                if (childPath.isArray()) {
                    ArrayChildPath arrayChildPath = childPath.toArrayChildPath();
                    curPath = arrayChildPath.getPath();
                    if (curPath == null) {
                        curPath = new Path(parentPath, shortName);
                        arrayChildPath.setPath(curPath);
                    }
                } else {
                    ObjectChildPath objectChildPath = childPath.toObjectChildPath();
                    curPath = objectChildPath.getPath(shortName);
                    if (curPath == null) {
                        curPath = new Path(parentPath, shortName);
                        objectChildPath.putPath(shortName, curPath);
                    }
                }
            }
            flag = true;
            String leafName = pathArray[len];
            Leaf leaf = getLeaf(curPath, isArrayPath(leafName));
            if (leaf.isElement()) {
                ElementLeaf elementLeaf = leaf.toElementLeaf();
                elementLeaf.setProtocol(Protocols.getProtocol(pathConfig.getProtocol(), clusterConfig.getProtocol()));
            } else {
                FieldLeaf fieldLeaf = leaf.toFieldLeaf();
                fieldLeaf.putField(leafName, Protocols.getProtocol(pathConfig.getProtocol(), clusterConfig.getProtocol()));
            }
        }
    }

    private ChildPath getChildPath(Path path, boolean isArray) {
        ChildPath childPath = path.getChild();
        if (childPath == null) {
            childPath = isArray ? new ArrayChildPath() : new ObjectChildPath();
            path.setChild(childPath);
        }

        if (childPath.isArray() != isArray) {
            throw new RuntimeException(path.toString() + " 类型冲突");
        }

        return childPath;
    }

    private Leaf getLeaf(Path path, boolean isArray) {
        Leaf leaf = path.getLeaf();
        if (leaf == null) {
            leaf = isArray ? new ElementLeaf() : new FieldLeaf();
            path.setLeaf(leaf);
        }
        if (leaf.isElement() != isArray) {
            throw new RuntimeException(path.toString() + " 类型冲突");
        }
        return leaf;
    }

    private boolean isArrayPath(String name) {
        return "[*]".equals(name);
    }

    public ClusterConfig getCluster() {
        return cluster;
    }
}
