package com.yd.http.testing.test;

import com.yd.http.testing.HttpUtil;

import java.util.HashMap;
import java.util.Map;

import static com.yd.http.testing.HttpClient.Request;

/**
 * @author created by Zeb灬D on 2019-11-05 17:51
 */
public class SignTest {

    //开发者签名校验
    public static void testQueryCityWeatherInfo() {
        String pattern = "/v1.0/developer/sign-check";
        Map<String, String> urlMap = new HashMap<>();
        Map<String, String> queryMap = new HashMap<>();
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("client_id", HttpUtil.ClientId);
        bodyMap.put("access_token", HttpUtil.AccessToken);
        bodyMap.put("sign", "");
        bodyMap.put("t", System.currentTimeMillis());
        bodyMap.put("sign_method", "HMAC-SHA256");
        bodyMap.put("device_id", "");

        Request("POST", pattern, urlMap, queryMap, bodyMap);
    }
}
