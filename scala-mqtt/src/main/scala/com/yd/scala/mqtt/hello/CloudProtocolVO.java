//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yd.scala.mqtt.hello;


import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;

public class CloudProtocolVO<P, T>  {
    private P protocol;
    private T data;
    private String pv;
    private long t;
    private String sign;

    public CloudProtocolVO() {
    }

    public P getProtocol() {
        return this.protocol;
    }

    public void setProtocol(P protocol) {
        this.protocol = protocol;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getPv() {
        return this.pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public long getT() {
        return this.t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static void main(String[] args) {
        String s = "{\"data\":\"PQs47wAkWG/uxRiWmR0Q4z96WwOzG0lYCaOesMMWkfDYX2C7/o9y1zEnTgMM+UdyY/XLviCSdeHbMzenduWiz/TXcMRfHMTssvnlSuMLuw1CnlROA+D46q5VVCDm9ozs+W1vwfHAO6prozLiuF61zLyX2dZcQq8mcdHJ+4q0hBPbxeEUK4Jggj9w+o3hdbeGjEGXI5o0JW1bWi5zyGKJyW1lbZJD2H8szPQt8jmJEAR8d+Y4WcNXme21hAyjvZzC\",\"protocol\":4,\"pv\":\"2.0\",\"sign\":\"b7d2bbaaae5420501f66aa222aaf16fb\",\"t\":1583801321659}";
        CloudProtocolVO data = JSONObject.parseObject(s,CloudProtocolVO.class);
        System.out.println(data);
        MessageVO messageVO = new MessageVO();
        messageVO.setEncodedMsg(JSONObject.toJSONString(data).getBytes(StandardCharsets.UTF_8));//消息体直接透传
        System.out.println(messageVO);
    }
}
