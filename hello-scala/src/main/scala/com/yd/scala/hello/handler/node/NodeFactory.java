package com.yd.scala.hello.handler.node;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yd.scala.hello.handler.path.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class NodeFactory {
    private static Logger logger = LoggerFactory.getLogger(NodeFactory.class);

    public NodeFactory() {
    }

    public static Node create(Path path, Object data) {
        try {
            if (data == null) {
                return null;
            } else if (data instanceof JSONArray) {
                return JSONArrayNode.create(path, (JSONArray) data);
            } else if (data instanceof JSONObject) {
                return JSONObjectNode.create(path, (JSONObject) data);
            } else if (data instanceof List) {
                return ListNode.create(path, (List) data);
            } else if (data instanceof Map) {
                return MapNode.create(path, (Map) data);
            } else {
                return (Node) (data.getClass().isArray() ? ArrayNode.create(path, (Object[]) ((Object[]) data)) : ObjectNode.create(path, data));
            }
        } catch (Exception var3) {
            logger.warn("create node失败，path={},data={}", path, data);
            return null;
        }
    }
}