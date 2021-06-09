//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yd.scala.mqtt.hello;

import com.alibaba.fastjson.JSONObject;

public class MessageVO {
    private String topic;
    private byte[] encodedMsg;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public byte[] getEncodedMsg() {
        return encodedMsg;
    }

    public void setEncodedMsg(byte[] encodedMsg) {
        this.encodedMsg = encodedMsg;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
