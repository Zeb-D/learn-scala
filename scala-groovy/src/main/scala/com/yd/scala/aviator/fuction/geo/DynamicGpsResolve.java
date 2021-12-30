package com.yd.scala.aviator.fuction.geo;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yd.scala.aviator.util.GpsUtil;

import java.util.Map;

/**
 * 动态地理围栏 表达式处理
 **/
public class DynamicGpsResolve extends AbstractFunction {
    public static final String NAME = "dynamicGpsResolve";

    /**
     * 计算地理围栏表达式
     *
     * @param env
     * @param arg1 dp点上报的GPS
     * @param arg2 中心点GPS
     * @return
     */
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        //设备上报GPS位置
        String dpGps = FunctionUtils.getStringValue(arg1, env);
        //中心点GPS位置
        String centerGps = FunctionUtils.getStringValue(arg2, env);
        GpsLocation location = GpsLocation.parseLocation(dpGps);
        GpsLocation startLocation = GpsLocation.parseLocation(centerGps);
        Double distance = GpsUtil.distFrom(location, startLocation);
        return AviatorLong.valueOf(distance.longValue());
    }


    @Override
    public String getName() {
        return NAME;
    }
}
