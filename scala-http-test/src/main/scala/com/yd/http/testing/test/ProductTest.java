package com.yd.http.testing.test;

import java.util.HashMap;
import java.util.Map;

import static com.yd.http.testing.HttpClient.Request;

/**
 * @author created by Zeb灬D on 2019-11-05 17:34
 */
public class ProductTest {

    //开发者账号查询产品列表
    public static void testQueryProducts() {
        String pattern = "/v1.0/developer/products";
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("category", "kg");
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("page_no", "1");
        queryMap.put("page_size", "10");
        Map<String, Object> bodyMap = null;

        Request("GET", pattern, urlMap, queryMap, bodyMap);
    }

    //开发者账号查询具体产品信息
    public static void testGetProduct() {
        String pattern = "/v1.0/products/{product_id}";
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("product_id", "ae5mce8exqcucrxz");
        Map<String, String> queryMap = new HashMap<>();
        Map<String, Object> bodyMap = null;

        Request("GET", pattern, urlMap, queryMap, bodyMap);
    }
}
