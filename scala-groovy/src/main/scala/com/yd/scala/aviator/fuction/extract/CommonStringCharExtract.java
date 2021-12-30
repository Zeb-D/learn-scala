package com.yd.scala.aviator.fuction.extract;

import com.googlecode.aviator.runtime.function.AbstractFunction;

import java.util.Map;

/**
 * 针对string类型dp点截取
 */
public abstract class CommonStringCharExtract extends AbstractFunction {
    public static final String STRINGVALUE = "stringValue";

    /**
     * 数据解析
     * @param env
     * @return
     */
    public String decode(Map<String, Object> env) {
        Object o = env.get(STRINGVALUE);
        if (o instanceof String) {
            return  (String) o;
        } else {
            return null;
        }
    }

    public String substring(String value,int index,int length) {
        return value.substring(index,index + length);
    }
}
