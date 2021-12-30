package com.yd.scala.aviator.fuction.extract;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import org.apache.commons.codec.binary.Base64;

import java.util.Map;

/**
 * 针对raw类型dp点截取
 */
public abstract class CommonRawByteExtract extends AbstractFunction {
    public static final String RAWVALUE = "rawValue";
    /**
     * 数据base64解码
     * @param env
     * @return
     */
    public byte[] decode(Map<String, Object> env) {
        Object o = env.get(RAWVALUE);
        byte[] bytes = null;
        if (o instanceof String) {
            String srt = (String) o;
            bytes = Base64.decodeBase64(srt);
        } else if (o instanceof byte[]) {
            byte[] srt = (byte[]) o;
            bytes = Base64.decodeBase64(srt);
        }
        return bytes;
    }
}
