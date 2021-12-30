package com.yd.scala.aviator.fuction;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class Exists extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        Object obj = arg1.getValue(env);
        boolean exists = obj != null;
        return AviatorBoolean.valueOf(exists);
    }

    public String getName() {
        return "exists";
    }
}


