package com.yd.scala.aviator.fuction.collection;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InRange extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String leftStr = "";
        if (arg1.getAviatorType().equals(AviatorType.String)) {
            leftStr = FunctionUtils.getStringValue(arg1, env);
        } else if (arg1.getAviatorType().equals(AviatorType.Long)) {
            leftStr = FunctionUtils.getNumberValue(arg1, env) + "";
        } else if (arg1.getAviatorType().equals(AviatorType.BigInt)) {
            leftStr = FunctionUtils.getNumberValue(arg1, env) + "";
        } else if (arg1.getAviatorType().equals(AviatorType.JavaType)) {
            Object leftObj = FunctionUtils.getJavaObject(arg1, env);
            if (leftObj instanceof Integer || leftObj instanceof Long || leftObj instanceof String) {
                leftStr = leftObj.toString();
            } else {
                leftStr = FunctionUtils.getStringValue(arg1, env);
            }
        }
        String right = FunctionUtils.getStringValue(arg2, env);
        List<String> rightList = Arrays.asList(right.split(","));
        boolean suc = rightList.contains(leftStr);
        return AviatorBoolean.valueOf(suc);
    }

    @Override
    public String getName() {
        return "inRange";
    }

}
