package com.yd.scala.aviator.fuction.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;

public class Extract extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String jsonValue = FunctionUtils.getStringValue(arg1, env);
        String pathExpr = FunctionUtils.getStringValue(arg2, env);
        JSON jsonObj = (JSON) JSON.parse(jsonValue);
        Object value = JSONPath.eval(jsonObj, pathExpr);

        if (value instanceof Boolean) {
            return AviatorBoolean.valueOf((Boolean) value);
        } else if (value instanceof String) {
            return new AviatorString((String) value);
        } else if (value instanceof Integer) {
            return AviatorLong.valueOf((Integer) value);
        } else if (value instanceof Long) {
            return AviatorLong.valueOf((Long) value);
        } else if (value instanceof BigDecimal) {
            return AviatorDecimal.valueOf((BigDecimal) value);
        } else if (value instanceof BigInteger) {
            return AviatorBigInt.valueOf((BigInteger) value);
        } else if (value instanceof Number) {
            return AviatorNumber.valueOf(value);
        }
        return null;
    }

    public String getName() {
        return "extract";
    }


}


