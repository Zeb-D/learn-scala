package com.yd.scala.aviator.fuction.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPath;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

public class HasField extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String jsonValue = FunctionUtils.getStringValue(arg1, env);
        String pathExpr = FunctionUtils.getStringValue(arg2, env);
        JSON jsonObj = (JSON) JSON.parse(jsonValue);
        boolean suc = JSONPath.contains(jsonObj, pathExpr);
        return AviatorBoolean.valueOf(suc);
    }

    public String getName() {
        return "hasField";
    }


}


