package com.yd.scala.aviator.fuction.backport;

import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * seq.list function to new an array list.
 */
public class SeqNewListFunction extends AbstractVariadicFunction {

    @Override
    public String getName() {
        return "seq.list";
    }

    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        List<Object> list = new ArrayList<>();

        for (AviatorObject obj : args) {
            list.add(obj.getValue(env));
        }

        return new AviatorRuntimeJavaType(list);
    }


}
