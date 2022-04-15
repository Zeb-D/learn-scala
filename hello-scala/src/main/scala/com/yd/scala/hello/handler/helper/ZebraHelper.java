package com.yd.scala.hello.handler.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yd.scala.hello.handler.cluster.ClusterConfig;
import com.yd.scala.hello.handler.leaf.ElementLeaf;
import com.yd.scala.hello.handler.leaf.FieldLeaf;
import com.yd.scala.hello.handler.leaf.Leaf;
import com.yd.scala.hello.handler.node.Node;
import com.yd.scala.hello.handler.node.NodeFactory;
import com.yd.scala.hello.handler.path.PathConfig;
import com.yd.scala.hello.handler.path.RootPath;
import com.yd.scala.hello.handler.protocol.DefaultProtocol;
import com.yd.scala.hello.handler.protocol.Protocol;
import com.yd.scala.hello.handler.protocol.Protocols;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZebraHelper {
    private static Logger logger = LoggerFactory.getLogger(ZebraHelper.class);

    public ZebraHelper() {
    }

    public static Object invoke(RootPath rootPath, Object data) {
        try {
            Node node = NodeFactory.create(rootPath, data);
            if (node == null) {
                return data;
            }

            invoke(node);
        } catch (Exception var3) {
            logger.error("数据脱敏发生错误,cluster={},data={}", rootPath.getCluster(), JSON.toJSONString(data));
        }

        return data;
    }

    private static void invoke(Node node) {
        if (node != null) {
            if (node.hasLeaf()) {
                Leaf leaf = node.getPath().getLeaf();
                if (leaf.isElement()) {
                    hideLeaf(leaf.toElementLeaf(), node);
                } else {
                    hideLeaf(leaf.toFieldLeaf(), node);
                }
            }

            if (node.hasChild()) {
                List<Node> childNodes = node.getChildren();
                Iterator var2 = childNodes.iterator();

                while (var2.hasNext()) {
                    Node childNode = (Node) var2.next();
                    invoke(childNode);
                }

            }
        }
    }

    private static void hideLeaf(ElementLeaf elementLeaf, Node node) {
        int size = node.getElementSize();

        for (int i = 0; i < size; ++i) {
            String value = node.getStringElementValue(i);
            value = elementLeaf.getProtocol().getHider().hide(value);
            node.setStringElementValue(i, value);
        }

    }

    private static void hideLeaf(FieldLeaf fieldLeaf, Node node) {
        List<String> fields = node.getFields();
        Iterator var3 = fields.iterator();

        while (var3.hasNext()) {
            String field = (String) var3.next();
            Protocol protocol = fieldLeaf.getProtocol(field);
            if (protocol != null) {
                String value = node.getStringFieldValue(field);
                value = protocol.getHider().hide(value);
                node.setStringFieldValue(field, value);
            }
        }

    }

    public static void main(String[] args) {
        Protocols.put("test", new DefaultProtocol());
        List<PathConfig> pathConfigs1 = new ArrayList();
        PathConfig pathConfig0 = new PathConfig();
        pathConfig0.setPath("$.resultDO.account");
        pathConfig0.setProtocol("test");
        pathConfigs1.add(pathConfig0);
        PathConfig pathConfig5 = new PathConfig();
        pathConfig5.setPath("$.resultDO.templateMsgParams");
        pathConfig5.setProtocol("test");
        pathConfigs1.add(pathConfig5);
        ClusterConfig clusterConfig2 = new ClusterConfig();
        clusterConfig2.setProtocol("test");
        clusterConfig2.setPathConfigs(pathConfigs1);
        RootPath rootPath1 = new RootPath(clusterConfig2);
        Object data0 = JSONObject.parse("{\"resultDO\":{\"lastLoginTime\":1571972502406,\"areaCode\":\"1\",\"phone\":\"\",\"name\":\"\",\"id\":\"az1571905052431gz5gL\",\"gmtCreate\":1571905052000,\"account\":\"13appreg2@bccto.me\",\"email\":\"13appreg2@bccto.me\",\"home\":[{\"homeId\":9776416,\"desc\":\"regression2\"}],\"templateMsgParams\":[\"1-2323323\",\"1-343543443\",\"1-232523452\"]}}");
        invoke(rootPath1, data0);
        System.out.println(JSON.toJSONString(data0));
        List<PathConfig> pathConfigs = new ArrayList();
        PathConfig pathConfig = new PathConfig();
        pathConfig.setPath("$.resultDO.list.[*].account");
        pathConfig.setProtocol("test");
        pathConfigs.add(pathConfig);
        PathConfig pathConfig1 = new PathConfig();
        pathConfig1.setPath("$.resultDO.list.[*].email");
        pathConfig1.setProtocol("test");
        pathConfigs.add(pathConfig1);
        PathConfig pathConfig3 = new PathConfig();
        pathConfig3.setPath("$.resultDO.list.[*].contacts.[*].phone");
        pathConfig3.setProtocol("test");
        pathConfigs.add(pathConfig3);
        ClusterConfig clusterConfig = new ClusterConfig();
        clusterConfig.setProtocol("test");
        clusterConfig.setPathConfigs(pathConfigs);
        RootPath rootPath = new RootPath(clusterConfig);
        Object data = JSON.parse("{\"resultDO\":{\"total\":30,\"offset\":0,\"limit\":20,\"list\":[{\"lastLoginTime\":1571972502406,\"areaCode\":\"1\",\"phone\":\"\",\"name\":\"\",\"id\":\"az1571905052431gz5gL\",\"gmtCreate\":1571905052000,\"account\":\"13appreg2@bccto.me\",\"email\":\"13appreg2@bccto.me\",\"home\":[{\"homeId\":9776416,\"desc\":\"regression2\"}]}]}}");
        Object data1 = JSON.parse("{\"resultDO\":{\"total\":30,\"offset\":0,\"limit\":20,\"list\":[{\"phone\":\"86-13157106810\",\"name\":\"haha\",\"account\":\"app121321@bccto.me\",\"activationCode\":\"7335\",\"areaCode\":\"86\",\"email\":\"appeu001@bccto.me\",\"gmtCreate\":1586223962000,\"gmtLastLogin\":1587781376108,\"id\":\"eu1586223962803vRW4O\",\"name\":\"haha\",\"phone\":\"86-13157106810\",\"contacts\":[{\"sequence\":1,\"phone\":\"86-124343444\"}]}]}}");
        invoke(rootPath, data);
        invoke(rootPath, data1);
        System.out.println(JSON.toJSONString(data));
        System.out.println(JSON.toJSONString(data1));
    }
}