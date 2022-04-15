package com.yd.scala.hello.template.parse.jsonpath;

import com.google.common.collect.Lists;
import com.yd.scala.hello.template.parse.jsonpath.annotation.JsonPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.List;

public class JsonPathObjectScanner {
    private static Logger log = LoggerFactory.getLogger(JsonPathObjectScanner.class);

    private static List<Class<?>> classList = Lists.newArrayList();

    private final static String BASE_PACKAGE = "com.xxx.infrastructure.httpsdk.valueobj";
    private final static String RESOURCE_PATTERN = "/*.class";

    static {
        log.info("scan annotation classes start!");
        long start = System.currentTimeMillis();
        classList.clear();
        scan();
        long end = System.currentTimeMillis();
        log.info("{} annotation classes scanned, cost time {} ms, ", classList.size(), end - start);
    }

    private static void scan() {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
                ClassUtils.convertClassNameToResourcePath(BASE_PACKAGE) + RESOURCE_PATTERN;
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            MetadataReaderFactory readerfactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                MetadataReader reader = readerfactory.getMetadataReader(resource);
                // 扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                // 判断是否有指定注解
                JsonPath jsonPath = clazz.getAnnotation(JsonPath.class);
                if (jsonPath != null) {
                    classList.add(clazz);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            log.error("scan annotation classes failed...");
        }
    }

    public static List<Class<?>> getJsonPathObject(){
        return classList;
    }
}
