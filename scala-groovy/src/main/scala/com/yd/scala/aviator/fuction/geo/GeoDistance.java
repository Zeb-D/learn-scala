package com.yd.scala.aviator.fuction.geo;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yd.scala.aviator.util.GpsUtil;

import java.util.Map;

public class GeoDistance extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        String left = FunctionUtils.getStringValue(arg1, env);
        String right = FunctionUtils.getStringValue(arg2, env);

        GpsLocation loc1 = GpsLocation.parseLocation(left);
        GpsLocation loc2 = GpsLocation.parseLocation(right);
        Double distance = GpsUtil.distFrom(loc1, loc2);

        return AviatorLong.valueOf(distance.longValue());
    }

    public String getName() {
        return "geoDistance";
    }

}
