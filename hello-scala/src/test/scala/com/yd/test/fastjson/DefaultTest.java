package com.yd.test.fastjson;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author created by Zeb灬D on 2021-09-26 15:28
 */
public class DefaultTest {
    public static void main(String[] args) {
        JSONObject object = new JSONObject();
        object.put("age", "111");
        object.put("name", null);//求 name的值；
        //object.put("name", "111"); //开启这行，求 name的值；

        System.out.println(object.toJSONString());

        U u = JSONObject.parseObject(object.toJSONString(), U.class);
        System.out.println(u);
    }

    @Data
    static class U {
        private String name = "";
        private Integer age;
    }
}
