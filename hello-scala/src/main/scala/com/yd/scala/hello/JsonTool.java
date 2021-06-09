package com.yd.scala.hello;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yd.json.JSON;
import com.yd.json.JSONArray;
import com.yd.json.JSONObject;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author created by Zeb灬D on 2020-08-11 16:14
 */
public class JsonTool {
    public static void main(String[] args) {
        //jackson调用方式
        String array = "[{\"name\":\"aaausername\",\"age\":12}]";
        JSONArray jsonArray = JSON.parseArray(array);
        for (Object obj : jsonArray) {
            JSONObject jsonObject = JSONObject.parseJSONObject(obj);
            System.out.println(jsonObject.toJSONString());
        }

        ArrayList<HashMap<String, String>> arrayList = JSONObject.parseObject(array, new TypeReference<ArrayList<HashMap<String, String>>>() {
        });
        System.out.println(arrayList);

        String s = "Asia/Shanghai";
        ZoneId zoneId = ZoneId.of(s);
        System.out.println(zoneId);
    }
}
