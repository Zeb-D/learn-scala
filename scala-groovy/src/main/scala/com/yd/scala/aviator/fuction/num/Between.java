package com.yd.scala.aviator.fuction.num;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class Between extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2, AviatorObject arg3) {
        long value = FunctionUtils.getNumberValue(arg1, env).longValue();
        long start = FunctionUtils.getNumberValue(arg2, env).longValue();
        long end = FunctionUtils.getNumberValue(arg3, env).longValue();

        boolean ok = value >= start && value <= end;
        return AviatorBoolean.valueOf(ok);
    }

    public String getName() {
        return "between";
    }
}


