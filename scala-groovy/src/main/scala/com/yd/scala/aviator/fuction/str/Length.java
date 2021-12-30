package com.yd.scala.aviator.fuction.str;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class Length extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String left = FunctionUtils.getStringValue(arg1, env);
        int len = left.length();
        return AviatorLong.valueOf(len);
    }

    public String getName() {
        return "length";
    }
}


