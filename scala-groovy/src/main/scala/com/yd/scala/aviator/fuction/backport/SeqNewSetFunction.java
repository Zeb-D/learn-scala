package com.yd.scala.aviator.fuction.backport;

import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorRuntimeJavaType;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * seq.set function to new a hash set.
 *
 * @author dennis
 * @since 4.1.2
 */
public class SeqNewSetFunction extends AbstractVariadicFunction {

    @Override
    public String getName() {
        return "seq.set";
    }

    @Override
    public AviatorObject variadicCall(Map<String, Object> env, AviatorObject... args) {
        Set<Object> set = new HashSet<>();

        for (AviatorObject obj : args) {
            set.add(obj.getValue(env));
        }

        return new AviatorRuntimeJavaType(set);
    }


}
