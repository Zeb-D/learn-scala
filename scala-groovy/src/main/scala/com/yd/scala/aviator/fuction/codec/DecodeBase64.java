package com.yd.scala.aviator.fuction.codec;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import org.apache.commons.codec.binary.Base64;

import java.util.Map;

public class DecodeBase64 extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1) {
        String rawValue = FunctionUtils.getStringValue(arg1, env);
        try {
            String value = new String(Base64.decodeBase64((String)rawValue),"UTF-8");
            return new AviatorString(value);
        } catch (Exception e) {
        }
        return null;
    }

    public String getName() {
        return "decodeBase64";
    }
}



