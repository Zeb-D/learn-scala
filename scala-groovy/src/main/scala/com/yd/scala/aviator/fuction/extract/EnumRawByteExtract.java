package com.yd.scala.aviator.fuction.extract;

import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;
import com.yd.scala.aviator.util.ByteUtils;
import lombok.SneakyThrows;

import java.util.Map;


/**
 * 针对raw类型dp点enum类型截取
 */
public class EnumRawByteExtract extends CommonRawByteExtract {

    /**
     * 将raw类型数值转换为对应 字符串 类型
     * @param env raw类型dp点数值
     * @param index  开始截取位置
     * @return
     */
    @SneakyThrows
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject index,AviatorObject length,AviatorObject charaset) {
        int num_index = FunctionUtils.getNumberValue(index,env).intValue();
        int num_length = FunctionUtils.getNumberValue(length,env).intValue();
        String charset = FunctionUtils.getStringValue(charaset,env);
        //解码后，raw类型dp点数据
        byte[] bytes = decode(env);
        //按规则截取
        byte[] subByte = ByteUtils.subByte(bytes, num_index, num_length);
        return subByte == null ? null: new AviatorString(new String(subByte, charset));
    }

    @Override
    public String getName() {
        return "enumRawByteExtract";
    }
}
