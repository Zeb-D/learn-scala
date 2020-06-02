package com.yd.scala.hello.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * kryo 序列化
 * author shenyanchao
 * @param <T>
 */
public class KryoSerializer<T> implements RedisSerializer<T> {

    private static final int BUFFER_SIZE = 2048;
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final ThreadLocal<Kryo> KRYOS = ThreadLocal.withInitial(Kryo::new);

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return EMPTY_BYTE_ARRAY;
        }
        // -1 代表无限制，实际中依业务修改
        Output output = new Output(BUFFER_SIZE, -1);
        Kryo kryo = KRYOS.get();
        kryo.writeClassAndObject(output, t);
        output.flush();
        return output.toBytes();
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        Input input = new Input(bytes);
        Kryo kryo = KRYOS.get();
        T t = (T) kryo.readClassAndObject(input);
        input.close();
        return t;
    }

}