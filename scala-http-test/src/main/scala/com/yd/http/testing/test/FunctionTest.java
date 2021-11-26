package com.yd.http.testing.test;

import com.yd.http.testing.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import static com.yd.http.testing.HttpClient.Request;

/**
 * @author created by Zeb灬D on 2019-11-05 16:02
 */
public class FunctionTest {

    //测试:获取产品分类下的指令集
    public static void testQueryFunctions() {
        String pattern = "/v1.0/functions/{category}";
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("category", "kg");
        Map<String, String> queryMap = null;
        Map<String, Object> bodyMap = null;

        Request("GET", pattern, urlMap, queryMap, bodyMap);
    }
}
