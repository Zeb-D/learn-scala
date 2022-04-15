package com.yd.scala.hello.editor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author created by Zeb灬D on 2021-08-31 16:42
 */
public class JSONPropertyEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        if (text == null || !StringUtils.hasText(text)) {
            throw new IllegalArgumentException("老大，不能为空啊！");
        } else {
            setValue(JSONObject.parseObject(text));
        }
    }

    public String getAsText() {
        return ((JSONObject) getValue()).toJSONString();
    }
}
