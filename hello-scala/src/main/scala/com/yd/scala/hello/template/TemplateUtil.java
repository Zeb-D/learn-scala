package com.yd.scala.hello.template;

import com.google.common.collect.Maps;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.yd.scala.hello.template.parse.jsonpath.JsonPathObjectScanner;
import com.yd.scala.hello.template.parse.jsonpath.JsonPathResolver;
import com.yd.scala.hello.template.parse.template.JtwigModelFactory;
import com.yd.scala.hello.template.parse.template.TemplateLoader;
import lombok.extern.slf4j.Slf4j;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 智能转换对象工具类
 *
 * @author lidan
 * @date 2020-11-17
 */
@Slf4j
@Component
public class TemplateUtil {

    private static JsonProvider jsonProvider = Configuration.defaultConfiguration().jsonProvider();

    @PostConstruct
    private void init() {
        // JsonPath:提前解析空数据，提高效率
        JsonPathObjectScanner.getJsonPathObject().forEach(clazz -> TemplateUtil.byJsonPath(clazz, ""));
        // JtwigTemplate:初始化模版
        TemplateUtil.byTemplate("template/hello.twig", Maps.newHashMap());
    }

    /**
     * 根据对象注解生成指定对象
     *
     * @param clazz      待转换对象类型
     * @param jsonString json字符串
     * @return T 待转换对象
     */
    public static <T> T byJsonPath(Class<T> clazz, String jsonString) {
        long start = System.currentTimeMillis();
        // 一次解析完文档
        Object document = jsonProvider.parse(jsonString);
        long end = System.currentTimeMillis();
        log.info("load document cost time: {}", end - start);
        return JsonPathResolver.getInstance().parseObject(clazz, document, "");
    }

    /**
     * 根据模版生成字符串
     *
     * @param templatePath 模版路径
     * @param variables    模版中变量来源，key与模版中变量保持一致，否则渲染不出
     * @return java.lang.String
     */
    public static String byTemplate(String templatePath, Map<String, Object> variables) {
        JtwigTemplate jtwigTemplate = TemplateLoader.getJtwigTemplate(templatePath);
        JtwigModel jtwigModel = JtwigModelFactory.createJtwigModel(variables);
        return jtwigTemplate.render(jtwigModel);
    }
}
