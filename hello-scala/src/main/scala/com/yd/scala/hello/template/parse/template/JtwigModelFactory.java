package com.yd.scala.hello.template.parse.template;

import org.jtwig.JtwigModel;

import java.util.Map;

/**
 * JtwigModel工厂类
 */
public class JtwigModelFactory {

    public static JtwigModel createJtwigModel(Map<String, Object> maps) {
        JtwigModel model = JtwigModel.newModel();
        maps.forEach(model::with);
        return model;
    }
}
