package com.yd.scala.hello.dubbo;

import com.yd.json.JSONObject;
import org.apache.dubbo.common.io.Bytes;

/**
 * @author created by Zeb灬D on 2021-11-19 22:35
 */
public class CodecTest {
    public static void main(String[] args) {
        byte[] header = new byte[16];
        Bytes.short2bytes((short)-9541, header);
        System.out.println(JSONObject.toJSONString(header));
    }
}
