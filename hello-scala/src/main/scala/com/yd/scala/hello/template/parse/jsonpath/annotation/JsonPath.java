package com.yd.scala.hello.template.parse.jsonpath.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonPath {
    /**
     * 路径，不填默认为空字符串。
     * 路径用.进行分隔离，如果为根路径，需要在前面加上$.。
     * 如果某个字段的类型是一个含有字段的对象，那路径以字段上注解的为准，但对应类依然需要用@MapPath注解标注。
     */
    String value() default "";

    /**
     * 别名列表。通过指定多个另外来支持多种字段类型
     *
     * @return 别名列表。
     */
    String[] alternate() default {};
}
