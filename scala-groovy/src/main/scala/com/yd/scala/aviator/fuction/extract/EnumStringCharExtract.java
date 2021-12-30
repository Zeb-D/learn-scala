package com.yd.scala.aviator.fuction.extract;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 针对string类型dp点enum类型截取
 */
public class EnumStringCharExtract extends CommonStringCharExtract {

    /**
     * 将string类型数值转换为对应 字符串 类型
     * @param env raw类型dp点数值
     * @param index  开始截取位置
     * @return
     */
    @SneakyThrows
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject index,AviatorObject length ) {
        int num_index = FunctionUtils.getNumberValue(index,env).intValue();
        int num_length = FunctionUtils.getNumberValue(length,env).intValue();
        //string类型dp点数据
        String dpvalue = decode(env);
        //按规则截取
        if (!StringUtils.isBlank(dpvalue)) {
            //按规则截取
            String str = substring(dpvalue,num_index, num_length);
            return new AviatorString(str);
        }
        return new AviatorString(dpvalue);
    }

    @Override
    public String getName() {
        return "enumStringCharExtract";
    }
}
