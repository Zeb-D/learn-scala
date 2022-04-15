package com.yd.scala.hello.template.parse.jsonpath;

import com.google.common.collect.Lists;
import com.yd.scala.hello.template.parse.jsonpath.annotation.JsonPath;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Json自定义路径解析
 * 可支持类型：基础类型及其包装类型、String、Enum、List<T>、自定义Object（里面字段类型为前几种）
 *
 */
public class JsonPathResolver {
    private static Logger log = LoggerFactory.getLogger(JsonPathResolver.class);
    // 根路径
    private final String ROOTPATH = "$";
    private final String DELIMITER = ".";

    private static JsonPathResolver jsonPathResolver = new JsonPathResolver();

    public static JsonPathResolver getInstance() {
        if (jsonPathResolver != null) {
            return jsonPathResolver;
        }
        return new JsonPathResolver();
    }

    public <T> T parseObject(Class<T> clazz, Object orgJson, String frontPath) {
        // 该类不存在JsonPath注解，则直接返回
        if (clazz == null || !clazz.isAnnotationPresent(JsonPath.class)) {
            return null;
        }
        String classPath = getClassPath(clazz, frontPath);
        // 逐个解析字段上的注解值
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        //log.info("{}->has annotation: {}, fileds_size:{}", clazz.toString(), clazz.isAnnotationPresent(JsonPath.class), fields.size());

        T obj = null;
        try {
            obj = clazz.getDeclaredConstructor().newInstance();
            setFieldsValue(orgJson, classPath, fields, obj);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("parse clazz:{} faild!", clazz.getName());
        }
        return obj;
    }

    private <T> void setFieldsValue(Object orgJson, String classPath, List<Field> fields, T finalObj) {
        fields.parallelStream().forEach(field -> {
            // 取消语言访问检查
            field.setAccessible(true);
            // 获取字段的类型
            Class<?> fieldType = field.getType();
            //log.info("field fieldType: {}", fieldType);
            // 字段带JsonPath注解则取其value值，否则取字段值
            String fieldValue = field.isAnnotationPresent(JsonPath.class) ?
                    field.getDeclaredAnnotation(JsonPath.class).value() : classPath + DELIMITER + field.getName();
            // Field路径
            String fieldPath = wrapFieldPath(classPath, fieldValue);
            // 获取字段数据
            Object fieldData = getFieldData(field, orgJson, classPath, fieldType, fieldPath);
            if (!Objects.isNull(fieldData)) {
                // 解析List类型
                if (fieldType == List.class) {
                    parseList(orgJson, finalObj, field, fieldPath, fieldData);
                }
                // 存在JsonPath注解需继续遍历解析
                else if (fieldType.isAnnotationPresent(JsonPath.class)) {
                    setFieldValue(finalObj, field, parseObject(fieldType, orgJson, fieldPath));
                }
                // 解析枚举
                else if (fieldType.isEnum()) {
                    Enum value = Enum.valueOf((Class<Enum>) fieldType, fieldData.toString());
                    setFieldValue(finalObj, field, value);
                }
                // 不存在JsonPath注解说明是基础类型、基础类型的包装类型、String，直接赋值；
                else if (!fieldType.isAnnotationPresent(JsonPath.class)) {
                    setFieldValue(finalObj, field, fieldData);
                }
            }
        });
    }

    private <T> String getClassPath(Class<T> clazz, String frontPath) {
        JsonPath declaredAnnotation = clazz.getDeclaredAnnotation(JsonPath.class);
        String currentClassValue = declaredAnnotation.value();
        // 如果当前类注解的值带$，则为绝对路径
        if (StringUtils.isNotBlank(currentClassValue)) {
            if (currentClassValue.startsWith(ROOTPATH)) {
                frontPath = currentClassValue;
            } else {
                frontPath = frontPath + DELIMITER + currentClassValue;
            }
        }
        return frontPath;
    }

    private <T> void parseList(Object orgJson, T finalObj, Field field, String fieldPath, Object fieldData) {
        try {
            Type genericType = field.getGenericType();
            // 泛型参数类型
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            Type[] actualTypes = parameterizedType.getActualTypeArguments();
            // 获取内嵌类型
            String name = actualTypes[0].getTypeName();
            Class<?> innerClass = Class.forName(name);
            //String read = JsonHelper.toString(fieldData);
            //List<?> listValues = JsonHelper.fromListString(read, innerClass);
            List<?> listValues = (List) fieldData;
            if (!innerClass.isAnnotationPresent(JsonPath.class)) {
                setFieldValue(finalObj, field, listValues);
            } else {
                if (CollectionUtils.isNotEmpty(listValues)) {
                    List list = Lists.newArrayList();
                    for (int i = 0; i < listValues.size(); i++) {
                        list.add(parseObject(innerClass, orgJson, fieldPath.replace("*", String.valueOf(i))));
                    }
                    setFieldValue(finalObj, field, list);
                }
            }
        } catch (ClassCastException | ClassNotFoundException e) {
            log.error("Field:{}, List must specify inline type!", field.getName());
        }
    }

    private <T> void setFieldValue(T obj, Field field, Object fieldData) {
        try {
            field.set(obj, fieldData);
        } catch (IllegalAccessException e) {
            log.error("set field value faild!");
        }
    }

    // 获取字段数据
    private Object getFieldData(Field field, Object orgJson, String frontPath, Class<?> fieldType, String fieldPath) {
        long start = System.currentTimeMillis();
        Object fieldData = parseField(orgJson, fieldType, fieldPath);
        // 取不到数据则通过别名路径取数据，否则跳过不解析该字段
        if (field.isAnnotationPresent(JsonPath.class) && Objects.isNull(fieldData)) {
            String[] alternates = field.getDeclaredAnnotation(JsonPath.class).alternate();
            for (String alternate : alternates) {
                fieldData = parseField(orgJson, fieldType, wrapFieldPath(frontPath, alternate));
                if (Objects.nonNull(fieldData)) {
                    break;
                }
            }
        }
        long end = System.currentTimeMillis();
        if (end - start > 1) {
            log.info("getFieldData cost time {} ms, fieldPath: {}", end - start, fieldPath);
        }
        return fieldData;
    }

    // 组装字段读取路径
    private String wrapFieldPath(String frontPath, String fieldValue) {
        if (!fieldValue.startsWith(ROOTPATH) && StringUtils.isNotBlank(fieldValue)) {
            // 相对路径需要补充上前置路径
            fieldValue = frontPath + DELIMITER + fieldValue;
        }
        return fieldValue;
    }

    // 根据路径解析数据
    private Object parseField(Object orgJson, Class<?> fieldType, String fieldValue) {
        long start = System.currentTimeMillis();
        try {
            //return com.jayway.jsonpath.JsonPath.read(orgJson, fieldValue);
            return com.jayway.jsonpath.JsonPath.parse(orgJson).read(fieldValue, fieldType);
        } catch (Exception e) {
            log.info("can not parse the path : {}", fieldValue);
        }
        long end = System.currentTimeMillis();
        if (end - start > 1) {
            log.info("jsonpath read data cost time {} ms, fieldPath: {}", end - start, fieldValue);
        }
        return null;
    }
}
