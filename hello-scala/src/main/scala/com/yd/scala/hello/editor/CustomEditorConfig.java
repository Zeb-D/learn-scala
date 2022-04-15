package com.yd.scala.hello.editor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author created by Zeb灬D on 2021-08-31 16:46
 */
@Component
public class CustomEditorConfig {

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer editorConfigurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>(4);
        customEditors.put(JSON.class, JSONPropertyEditor.class);
        customEditors.put(JSONObject.class, JSONPropertyEditor.class);
        editorConfigurer.setCustomEditors(customEditors);
        return editorConfigurer;
    }
}
