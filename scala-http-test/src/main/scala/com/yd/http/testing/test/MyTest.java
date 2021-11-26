package com.yd.http.testing.test;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author created by Zeb灬D on 2020-05-09 11:11
 */
public class MyTest {
    public static void main(String[] args) {
        String s= "{\"20200421\":[\"\",\"kpjfqfsytmjg9kcsg4pu\",\"d4e8gq3drth9yrjfpyvt\",\"efsk5eu7ypt3eq7am7as\",\"4qynd75cfm4tkm4838hx\",\"hf3taqkqtst4hvfyqnge\",\"e4h9fca7hvhykut4ruya\",\"qajyntv9j3ph5d3f4wuc\",\"xfx7u5eeef7srjjwwmhv\",\"jnxytchcnf73qeshjtpd\",\"5vahnjk4u3gf73vgr3qg\",\"pm3kss5mn9hqnjjrmupn\",\"7egnd3twqupee5xntfee\",\"e9fy7sqxp5snyjv3drvu\",\"avcxhe9sv9n5xwxyfc9e\",\"f54m9j4d53n59emhwmds\",\"guy4ja348suksvp4x9sx\",\"4s3em5puam3vcrcfrdnq\",\"98kehmryseeqtpw45qmg\",\"ahum9xw937vjycuwhghp\"]}";
        Map m = JSONObject.parseObject(s, Map.class);
        System.out.println(m.size());
    }
}
