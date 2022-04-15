package com.yd.scala.hello.template.parse.template;

import com.google.common.collect.Maps;
import org.jtwig.JtwigTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 模版加载器
 */
public class TemplateLoader {
    private static Logger log = LoggerFactory.getLogger(TemplateLoader.class);

    private static Map<String, JtwigTemplate> templateMap = Maps.newHashMap();

    public static JtwigTemplate getJtwigTemplate(String path) {
        JtwigTemplate template = TemplateLoader.templateMap.get(path);
        if (template == null) {
            log.info("load template {}", path);
            template = JtwigTemplate.classpathTemplate(path);
            TemplateLoader.templateMap.put(path, template);
        }
        return template;
    }
}
