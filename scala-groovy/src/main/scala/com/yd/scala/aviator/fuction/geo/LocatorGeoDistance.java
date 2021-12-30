package com.yd.scala.aviator.fuction.geo;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yd.scala.aviator.util.GpsUtil;

import java.util.Map;

public class LocatorGeoDistance extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        GpsLocation location = GpsLocation.parseLocation(FunctionUtils.getStringValue(arg1, env));
        GpsLocation startLocation = GpsLocation.parseLocation(FunctionUtils.getStringValue(arg2, env));
        Double distance = GpsUtil.distFrom(location, startLocation);
        return AviatorLong.valueOf(distance.longValue());
    }

    @Override
    public String getName() {
        return "locatorGeoDistance";
    }
}
