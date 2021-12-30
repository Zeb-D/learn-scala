package com.yd.scala.aviator.fuction.geo;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import com.googlecode.aviator.runtime.type.AviatorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

//条件表达式，//目前只应用于 {"||" [] [] [] } 这种形式,函数调用和比较操作都是用JSONArray
public class CondCalculate extends AbstractFunction {

    private static final Logger logger = LoggerFactory.getLogger(CondCalculate.class);

    private static final String CAL_TYPE = "calType";

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        JSONObject calObj = (JSONObject) FunctionUtils.getJavaObject(arg2, env);

        if (calObj == null || calObj.get(CAL_TYPE) == null) {
            logger.warn("CondCalculateError, arg1={}, arg2={}", arg1, arg2);
            String result = "";
            return new AviatorString(result);
        }
        if ("duration".equals(calObj.get(CAL_TYPE))) {
            if (arg1.getAviatorType() == AviatorType.Boolean) {
                logger.info("CondCalculateRestule,arg1={},env:{}", arg1.booleanValue(env), JSONObject.toJSONString(env));
                return AviatorBoolean.valueOf(arg1.booleanValue(env));
            } else {
                logger.info("CondCalculateRestuleFalse,arg1Type={}", arg1.getAviatorType());
                return AviatorBoolean.FALSE;
            }
        }
        return AviatorBoolean.TRUE;
    }

    @Override
    public String getName() {
        return "condCalculate";
    }
}
