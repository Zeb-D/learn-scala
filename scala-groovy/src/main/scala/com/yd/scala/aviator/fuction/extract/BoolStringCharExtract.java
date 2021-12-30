package com.yd.scala.aviator.fuction.extract;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 针对String类型dp点bool类型截取
 */
public class BoolStringCharExtract extends CommonStringCharExtract {

    /**
     * 将string类型数值转换为对应bool类型
     * @param env string类型dp点数值
     * @param index  开始截取位置
     * @param length 截取长度
     * @return
     */
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject index,AviatorObject length) {
        int num_index = FunctionUtils.getNumberValue(index,env).intValue();
        int num_length = FunctionUtils.getNumberValue(length,env).intValue();
        //string类型dp点数据
        String dpvalue = decode(env);
        if (!StringUtils.isBlank(dpvalue)) {
            //按规则截取
            String str = substring(dpvalue,num_index, num_length);
            Integer integer = Integer.valueOf(str);
            boolean b = integer.intValue() == 1 ? true : false;
            return AviatorBoolean.valueOf(b);
        }
        return null;
    }

    @Override
    public String getName() {
        return "boolStringCharExtract";
    }
}
