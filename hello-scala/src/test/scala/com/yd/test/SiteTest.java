package com.yd.test;

import com.yd.json.JSONObject;
import org.junit.Test;

/**
 * @author created by Zeb灬D on 2021-08-26 15:58
 */
public class SiteTest {
    @Test
    public void test(){
        System.out.println(JSONObject.toJSONString(SiteEnum.listCountry()));
    }
}
