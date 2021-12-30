package com.yd.scala.aviator.fuction.extract;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorLong;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.yd.scala.aviator.util.ByteUtils;

import java.util.Map;

/**
 * 针对raw类型dp点value类型截取
 */
public class ValueRawByteExtract extends CommonRawByteExtract {

    /**
     * 将raw类型数值转换为对应int类型
     * @param env raw类型dp点数值
     * @param index  开始截取位置
     * @param length 截取长度
     * @return
     */
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject index, AviatorObject length) {
        int num_index = FunctionUtils.getNumberValue(index,env).intValue();
        int num_length = FunctionUtils.getNumberValue(length,env).intValue();
        //解码后，raw类型dp点数据
        byte[] bytes = decode(env);
        //按规则截取
        byte[] subByte = ByteUtils.subByte(bytes, num_index, num_length);
        return subByte == null ? null: AviatorLong.valueOf(ByteUtils.bytes2Int(subByte));
    }

    @Override
    public String getName() {
        return "valueRawByteExtract";
    }
}
