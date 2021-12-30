package com.yd.scala.aviator.fuction.bit;


import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yd.scala.aviator.util.BitUtils;

import java.util.Map;

public class BitNotSetted extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number left = FunctionUtils.getNumberValue(arg1, env);
        Number right = FunctionUtils.getNumberValue(arg2, env);

        long number = left.longValue();
        int idx = right.intValue();

        return AviatorBoolean.valueOf(!BitUtils.isTrue(number, idx));
    }

    public String getName() {
        return "bitNotEq";
    }
}


