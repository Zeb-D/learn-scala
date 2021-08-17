package com.yd.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.yd.json.deserializer.MyUntypeedObjectDeserializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class JSON {

    /**
     * 反序列化的要使用的feature +++
     */
    private static List<DeserializationFeature> deserializationEnabledDefaultFeature;

    private static List<JsonParser.Feature> deserializationEnabledJsonParserFeature;

    /**
     * 反序列化的要禁用的feature ---
     */
    private static List<DeserializationFeature> deserializationDisabledDefaultFeature;

    private static List<JsonParser.Feature> deserializationDisabledJsonParserFeature;

    /**
     * 序列化要使用的feature +++
     */
    private static List<SerializationFeature> serializationEnabledDefaultFeature;

    private static List<JsonGenerator.Feature> serializationEnabledJsonGeneratorFeature;

    /**
     * 序列化要禁用的feature ---
     */
    private static List<SerializationFeature> serializationDisabledDefaultFeature;

    private static List<JsonGenerator.Feature> serializationDisabledJsonGeneratorFeature;

    static {
        deserializationEnabledDefaultFeature = new ArrayList<>();
        deserializationEnabledJsonParserFeature = new ArrayList<>();
        deserializationEnabledJsonParserFeature.add(JsonParser.Feature.AUTO_CLOSE_SOURCE);
        deserializationEnabledDefaultFeature.add(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        deserializationEnabledJsonParserFeature.add(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        deserializationEnabledJsonParserFeature.add(JsonParser.Feature.ALLOW_SINGLE_QUOTES);

        deserializationDisabledDefaultFeature = new ArrayList<>();
        deserializationDisabledJsonParserFeature = new ArrayList<>();
        deserializationDisabledDefaultFeature.add(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    static {
        serializationEnabledDefaultFeature = new ArrayList<>();
        serializationEnabledJsonGeneratorFeature = new ArrayList<>();
        serializationEnabledJsonGeneratorFeature.add(JsonGenerator.Feature.QUOTE_FIELD_NAMES);
        serializationEnabledDefaultFeature.add(SerializationFeature.CLOSE_CLOSEABLE);
        serializationEnabledDefaultFeature.add(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);

        serializationDisabledDefaultFeature = new ArrayList<>();
        serializationDisabledJsonGeneratorFeature = new ArrayList<>();
        serializationDisabledDefaultFeature.add(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        serializationDisabledDefaultFeature.add(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    /**
     * 将字符串反序列化为JSONObject对象(增加feature)
     *
     * @param text
     * @return
     */
    public static JSONObject parseObject(String text, DeserializationFeature... features) {
        if (null == text || 0 == text.length()) {
            return null;
        }

        ObjectMapper objectMapper = getDeserializerObjectMapper(features);
        JSONObject jsonObject;
        try {
            jsonObject = objectMapper.readValue(text, JSONObject.class);
            jsonObject.setJsonNode(getJsonNode(text));
        } catch (JsonProcessingException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return jsonObject;
    }

    public static JSONObject parseObject(String text) {
        if (null == text || 0 == text.length()) {
            return null;
        }

        return parseObject(text, new DeserializationFeature[0]);
    }

    /**
     * 将字符串反序列化为指定的对象(泛型)
     *
     * @param text
     * @param type
     * @param features
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, TypeReference<T> type, DeserializationFeature... features) {
        if (null == text || 0 == text.length()) {
            return null;
        }

        ObjectMapper objectMapper = getDeserializerObjectMapper(features);
        T t;
        try {
            t = objectMapper.readValue(text, type);
        } catch (JsonProcessingException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return t;
    }

    public static <T> T parseObject(String text, TypeReference<T> type) {
        if (null == text || 0 == text.length()) {
            return null;
        }
        return parseObject(text, type, new DeserializationFeature[0]);
    }

    /**
     * 将字符串反序列化为指定的对象(javaType类型)
     *
     * @param text
     * @param type
     * @param features
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, JavaType type, DeserializationFeature... features) {
        if (null == text || 0 == text.length()) {
            return null;
        }
        ObjectMapper objectMapper = getDeserializerObjectMapper(features);
        T t;
        try {
            t = objectMapper.readValue(text, type);
        } catch (JsonProcessingException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return t;
    }

    public static <T> T parseObject(String text, JavaType type) {
        if (null == text || 0 == text.length()) {
            return null;
        }
        return parseObject(text, type, new DeserializationFeature[0]);
    }

    /**
     * 将字符串反序列化为指定的对象(指定clazz)
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String text, Class<T> clazz, DeserializationFeature... features) {
        if (null == text || 0 == text.length()) {
            return null;
        }
        ObjectMapper objectMapper = getDeserializerObjectMapper(features);
        T value;
        try {
            value = objectMapper.readValue(text, clazz);
        } catch (JsonProcessingException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return value;
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return parseObject(text, clazz, new DeserializationFeature[0]);
    }

    /**
     * 将字符串反序列化为JSONArray
     *
     * @param text
     * @return
     */
    public static JSONArray parseArray(String text) {

        if (null == text || 0 == text.length()) {
            return null;
        }

        if (!text.startsWith(JsonToken.START_ARRAY.asString())) {
            throw new IllegalArgumentException("parameter must start with [");
        }

        ObjectMapper objectMapper = getDeserializerObjectMapper(null);

        // 1. not contain "{", eg "[\"zhangsan\", \"lisi\"]"
        if (!text.contains(JsonToken.START_OBJECT.asString())) {
            try {
                return objectMapper.readValue(text, new TypeReference<JSONArray>() {

                });
            } catch (JsonProcessingException e) {
                throw new JSONException(e.getMessage(), e);
            }
        }

        // 2. contain "{", eg "[{\"username\":\"zhangsan\",\"age\":14}, {\"username\":\"lisi\", \"age\":15}]"
        try {
            List<Object> list = objectMapper.readValue(text,
                    TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, JSONObject.class));
            return new JSONArray(list);

        } catch (JsonProcessingException e) {
            // 3. fail, transfer element to linkedhashmap in array. eg "[{\"username\":\"zhangsan\"}, \"lisi\"]"
            try {
                return objectMapper.readValue(text, new TypeReference<JSONArray>() {

                });
            } catch (JsonProcessingException ex) {
                throw new JSONException(ex.getMessage(), ex);
            }
        }

    }

    /**
     * 将字符串反序列化为List<T>
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (null == text || 0 == text.length()) {
            return null;
        }
        ObjectMapper objectMapper = getDeserializerObjectMapper(null);
        List<T> list;
        try {
            list = objectMapper
                    .readValue(text, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
        } catch (JsonProcessingException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return list;
    }

    public static <T> T toJavaObject(JSON json, Class<T> clazz) {
        if (null == json) {
            return null;
        }
        return parseObject(json.toString(), clazz);
    }

    //---------------------------------------以上为   反序列化----------------------------------------------------------------

    /**
     * 将对象序列化为json串（优美的格式）
     *
     * @param object
     * @param prettyFormat
     * @return
     */
    public static String toJSONString(Object object, boolean prettyFormat) {
        if (null == object) {
            return null;
        }
        ObjectMapper objectMapper = getSerializerObjectMapper(null);
        String jsonString;
        try {
            if (prettyFormat) {
                jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } else {
                jsonString = objectMapper.writeValueAsString(object);
            }
        } catch (JsonProcessingException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return jsonString;
    }

    /**
     * 将对象序列化为json串
     *
     * @param object
     * @param features
     * @return
     */
    public static String toJSONString(Object object, SerializationFeature... features) {
        if (null == object) {
            return null;
        }
        ObjectMapper objectMapper = getSerializerObjectMapper(features);
        String jsonString;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return jsonString;
    }

    public static String toJSONString(Object object) {
        if (null == object) {
            return null;
        }
        return toJSONString(object, false);
    }

    /**
     * 将对象序列化成json写到writer里
     *
     * @param writer
     * @param object
     * @param features
     */
    public static void writeJSONString(Writer writer, Object object, SerializationFeature... features) {
        if (null == object) {
            return;
        }
        ObjectMapper objectMapper = getSerializerObjectMapper(features);
        try {
            objectMapper.writeValue(writer, object);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static void writeJSONString(Writer writer, Object object) {
        if (null == object) {
            return;
        }
        writeJSONString(writer, object, new SerializationFeature[0]);
    }

    /**
     * 将对象序列化成json byte数组
     *
     * @param object
     * @param features
     * @return
     */
    public static byte[] toJSONBytes(Object object, SerializationFeature... features) {
        if (null == object) {
            return null;
        }
        ObjectMapper objectMapper = getSerializerObjectMapper(features);
        byte[] bytes;
        try {
            bytes = objectMapper.writeValueAsBytes(object);
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
        return bytes;
    }

    public static byte[] toJSONBytes(Object object) {
        if (null == object) {
            return null;
        }
        return toJSONBytes(object, new SerializationFeature[0]);
    }

    /**
     * 将对象序列化成json写到outputStream里
     *
     * @param outputStream
     * @param jsonEncoding
     * @param object
     * @param features
     * @throws IOException
     */
    public static void writeJSONString(OutputStream outputStream, JsonEncoding jsonEncoding, Object object,
                                       SerializationFeature... features) throws IOException {
        if (null == object) {
            return;
        }
        ObjectMapper objectMapper = getSerializerObjectMapper(features);
        try {
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, jsonEncoding);
            objectMapper.writeValue(jsonGenerator, object);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void writeJSONString(OutputStream outputStream, Object object, SerializationFeature... features)
            throws IOException {
        if (null == object) {
            return;
        }
        writeJSONString(outputStream, JsonEncoding.UTF8, object, features);
    }

    /**
     * 对map进行指定方式的序列化
     * <p>如果指定了{@code keySerializer} 或者 {@code valueSerializer}, 则启用特定的{@code ObjectMapper}并配合{@code keySerializer} 和 {@code valueSerializer}进行序列化
     * 否则使用全局唯一的{@code ObjectMapper}进行标准的序列化
     * </p>
     * <p>
     * 注意：使用此方法请确保了解{@code JsonSerializer}和相关的jackson底层实现机制，否则建议直接使用{@link JSON#toJSONString(Object)}进行序列化即可
     * </p>
     *
     * @param map             需要序列化的map
     * @param ignoredKey      需要忽略的key
     * @param keySerializer   key的序列化器
     * @param valueSerializer value的序列化器
     * @return
     */
    public static String toJSONString(Map map, Set<String> ignoredKey, JsonSerializer<Object> keySerializer,
                                      JsonSerializer<Object> valueSerializer) {
        if (null == map || 0 == map.size()) {
            return null;
        }

        if (null != keySerializer || null != valueSerializer) {
            // TODO cache ObjectMapper?
            ObjectMapper objectMapper = getNewDeserializationObjectMapper();
            MapSerializer mapSerializer = MapSerializer
                    .construct(ignoredKey, null, false, null, keySerializer, valueSerializer, null);
            List<JsonSerializer<?>> serializers = new ArrayList<>();
            serializers.add(mapSerializer);
            SerializerFactory beanSerializerFactory = BeanSerializerFactory.instance
                    .withAdditionalSerializers(new SimpleSerializers(serializers));
            objectMapper.setSerializerFactory(beanSerializerFactory);

            try {
                return objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                throw new JSONException(e.getMessage(), e);
            }
        }

        return JSON.toJSONString(map);

    }

    //-----------------------------------------以上   序列化-----------------------------------------------------------------

    /**
     * 供子类JSONObject或者JSONArray转换使用
     *
     * @param object
     * @return
     */
    protected static JSONObject toJSONObject(Object object) {
        if (null == object) {
            return null;
        }
        JSONObject jsonObject;
        try {
            String jsonString = toJSONString(object);
            jsonObject = parseObject(jsonString);
        } catch (JSONException e) {
            throw e;
        }
        return jsonObject;
    }

    protected <T> T toGivenObject(Object object, Class<T> clazz) {
        if (null == object) {
            return null;
        }
        T t;
        try {
            String jsonString = toJSONString(object);
            t = parseObject(jsonString, clazz);
        } catch (JSONException e) {
            throw e;
        }
        return t;
    }

    protected <T> T toGivenObject(Object object, JavaType type) {
        if (null == object) {
            return null;
        }
        T t;
        try {
            String jsonString = toJSONString(object);
            t = parseObject(jsonString, type);
        } catch (JSONException e) {
            throw e;
        }
        return t;
    }

    protected <T> T toGivenObject(Object object, TypeReference<T> type) {
        if (null == object) {
            return null;
        }
        T t;
        try {
            String jsonString = toJSONString(object);
            t = parseObject(jsonString, type);
        } catch (JSONException e) {
            throw e;
        }
        return t;
    }

    /**
     * 供子类JSONObject或者JSONArray转换使用
     *
     * @param object
     * @return
     */
    protected static JSONArray toJSONArray(Object object) {
        if (null == object) {
            return null;
        }
        JSONArray jsonArray;
        try {
            String jsonString = toJSONString(object);
            jsonArray = parseArray(jsonString);
        } catch (JSONException e) {
            throw e;
        }
        return jsonArray;
    }

    protected static JsonNode getJsonNode(String text) {
        if (null == text || 0 == text.length()) {
            return null;
        }
        ObjectMapper objectMapper = getNewDeserializationObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(text);
        } catch (Exception e) {
            throw new JSONException(e.getMessage());
        }
        return jsonNode;
    }

    //-----------------------------------------以上  子类通用方法-----------------------------------------------------------------

    @Override public String toString() {
        return toJSONString();
    }

    public String toJSONString() {
        return toJSONString(this);
    }

    private static ObjectMapper shareObjectMapper = new ObjectMapper();

    static {
        // init deserialization config
        deserializationEnabledDefaultFeature.stream().forEach(it -> shareObjectMapper.configure(it, true));
        deserializationEnabledJsonParserFeature.stream().forEach(it -> shareObjectMapper.configure(it, true));
        deserializationDisabledDefaultFeature.stream().forEach(it -> shareObjectMapper.configure(it, false));
        deserializationDisabledJsonParserFeature.stream().forEach(it -> shareObjectMapper.configure(it, false));

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Object.class, new MyUntypeedObjectDeserializer(null, null));
        shareObjectMapper.registerModule(simpleModule);

        // init serialization config
        shareObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        serializationEnabledDefaultFeature.stream().forEach(it -> shareObjectMapper.configure(it, true));
        serializationEnabledJsonGeneratorFeature.stream().forEach(it -> shareObjectMapper.configure(it, true));
        serializationDisabledDefaultFeature.stream().forEach(it -> shareObjectMapper.configure(it, false));
        serializationDisabledJsonGeneratorFeature.stream().forEach(it -> shareObjectMapper.configure(it, false));
    }

    private static ObjectMapper getNewDeserializationObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        deserializationEnabledDefaultFeature.stream().forEach(it -> objectMapper.configure(it, true));
        deserializationEnabledJsonParserFeature.stream().forEach(it -> objectMapper.configure(it, true));
        deserializationDisabledDefaultFeature.stream().forEach(it -> objectMapper.configure(it, false));
        deserializationDisabledJsonParserFeature.stream().forEach(it -> objectMapper.configure(it, false));

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Object.class, new MyUntypeedObjectDeserializer(null, null));
        objectMapper.registerModule(simpleModule);

        return objectMapper;
    }

    private static ObjectMapper getNewSerializationObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        serializationEnabledDefaultFeature.stream().forEach(it -> objectMapper.configure(it, true));
        serializationEnabledJsonGeneratorFeature.stream().forEach(it -> objectMapper.configure(it, true));
        serializationDisabledDefaultFeature.stream().forEach(it -> objectMapper.configure(it, false));
        serializationDisabledJsonGeneratorFeature.stream().forEach(it -> objectMapper.configure(it, false));
        return objectMapper;
    }

    private static ObjectMapper getDeserializerObjectMapper(DeserializationFeature... features) {

        ObjectMapper objectMapper;
        if (null == features || 0 == features.length) {
            objectMapper = shareObjectMapper;
        } else {
            // TODO cache ObjectMapper?
            objectMapper = getNewDeserializationObjectMapper();
            objectMapper.getDeserializationConfig().withFeatures(features);
        }
        return objectMapper;
    }

    private static ObjectMapper getSerializerObjectMapper(SerializationFeature... features) {
        ObjectMapper objectMapper;
        if (null == features || 0 == features.length) {
            objectMapper = shareObjectMapper;
        } else {
            objectMapper = getNewSerializationObjectMapper();
            objectMapper.getSerializationConfig().withFeatures(features);
        }
        return objectMapper;
    }

}
