package com.yd.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private final Map<String, Object> map;

    private JsonNode jsonNode;

    public JSONObject() {
        this(DEFAULT_INITIAL_CAPACITY, false);
    }

    public void setJsonNode(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    public JSONObject(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("map is null.");
        }
        this.map = map;
    }

    public JSONObject(boolean ordered) {
        this(DEFAULT_INITIAL_CAPACITY, ordered);
    }

    public JSONObject(int initialCapacity) {
        this(initialCapacity, false);
    }

    public JSONObject(int initialCapacity, boolean ordered) {
        if (ordered) {
            map = new LinkedHashMap<String, Object>(initialCapacity);
        } else {
            map = new HashMap<String, Object>(initialCapacity);
        }
    }

    public static JSONObject parseJSONObject(Object value) {
        if (null == value) {
            return null;
        }
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }

        if (value instanceof Map) {
            return new JSONObject((Map) value);
        }

        if (value instanceof String) {
            return parseObject((String) value);
        }
        return toJSONObject(value);
    }

    public JSONObject getJSONObject(String key) {
        Object value = map.get(key);
        if (null == value) {
            return null;
        }
        if (value instanceof JSONObject) {
            return (JSONObject) value;
        }

        if (value instanceof Map) {
            return new JSONObject((Map) value);
        }

        if (value instanceof String) {
            return parseObject((String) value);
        }
        return toJSONObject(value);
    }

    public JSONArray getJSONArray(String key) {
        Object value = map.get(key);
        if (null == value) {
            return null;
        }
        if (value instanceof JSONArray) {
            return (JSONArray) value;
        }

        if (value instanceof List) {
            return toJSONArray(value);
        }

        if (value instanceof String) {
            return parseArray((String) value);
        }
        return toJSONArray(value);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        Object value = map.get(key);
        return toGivenObject(value, clazz);
    }

    public <T> T getObject(String key, JavaType type) {
        Object value = map.get(key);
        return toGivenObject(value, type);
    }

    public <T> T getObject(String key, TypeReference typeReference) {
        Object value = map.get(key);
        if (typeReference == null) {
            return (T) value;
        }
        return (T) toGivenObject(value, typeReference);
    }

    public Boolean getBoolean(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isBoolean()) {
            return Boolean.valueOf(jsonNodeForKey.booleanValue());
        }
        try {
            return Boolean.valueOf(jsonNodeForKey.asText());
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean getBooleanValue(String key) {
        Boolean value = getBoolean(key);
        if (null == value) {
            return false;
        }
        return value.booleanValue();
    }

    public byte[] getBytes(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = jsonNodeForKey.binaryValue();
        } catch (IOException e) {
            throw new JSONException("can not cast to byte[]");
        }
        return bytes;
    }

    public Short getShort(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isShort()) {
            return Short.valueOf(jsonNodeForKey.shortValue());
        }
        try {
            return Short.valueOf(jsonNodeForKey.asText());
        } catch (Exception e) {
            throw e;
        }
    }

    public short getShortValue(String key) {
        Short value = getShort(key);
        if (null == value) {
            return 0;
        }
        return value.shortValue();
    }

    public Integer getInteger(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isInt()) {
            return Integer.valueOf(jsonNodeForKey.intValue());
        }
        try {
            return Integer.parseInt(jsonNodeForKey.asText());
        } catch (Exception e) {
            throw e;
        }
    }

    public int getIntValue(String key) {
        Integer value = getInteger(key);
        if (null == value) {
            return 0;
        }
        return value.intValue();
    }

    public Long getLong(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isLong()) {
            return Long.valueOf(jsonNodeForKey.longValue());
        }
        try {
            return Long.parseLong(jsonNodeForKey.asText());
        } catch (Exception e) {
            throw e;
        }
    }

    public long getLongValue(String key) {
        Long value = getLong(key);
        if (null == value) {
            return 0;
        }
        return value.longValue();
    }

    public Float getFloat(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isFloat()) {
            return Float.valueOf(jsonNodeForKey.floatValue());
        }
        try {
            return Float.parseFloat(jsonNodeForKey.asText());
        } catch (Exception e) {
            throw e;
        }
    }

    public float getFloatValue(String key) {
        Float value = getFloat(key);
        if (null == value) {
            return 0;
        }
        return value.floatValue();
    }

    public Double getDouble(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isDouble()) {
            return Double.valueOf(jsonNodeForKey.doubleValue());
        }
        try {
            return Double.parseDouble(jsonNodeForKey.asText());
        } catch (Exception e) {
            throw e;
        }
    }

    public double getDoubleValue(String key) {
        Double value = getDouble(key);
        if (null == value) {
            return 0;
        }
        return value.doubleValue();
    }

    public BigDecimal getBigDecimal(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isBigDecimal()) {
            return jsonNodeForKey.decimalValue();
        }
        try {
            Double doubleValue = getDouble(key);
            return BigDecimal.valueOf(doubleValue);
        } catch (Exception e) {
        }
        try {
            Long longValue = getLong(key);
            return BigDecimal.valueOf(longValue);
        } catch (Exception e) {
        }
        throw new JSONException("can not cast to bigDecimal");
    }

    public BigInteger getBigInteger(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isBigInteger()) {
            return jsonNodeForKey.bigIntegerValue();
        }
        try {
            Long longValue = getLong(key);
            return BigInteger.valueOf(longValue);
        } catch (Exception e) {
        }
        throw new JSONException("can not cast to bigInteger");
    }

    public String getString(String key) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(key);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isTextual()) {
            return jsonNodeForKey.asText();
        }
        return jsonNodeForKey.toString();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        throw new RuntimeException("method call not supported");
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        boolean result = map.containsKey(key);
        if ((!result) && key instanceof Number) {
            result = map.containsKey(key.toString());
        }
        return result;
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        Object val = map.get(key);
        if (val == null && key instanceof Number) {
            val = map.get(key.toString());
        }
        return val;
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    public JSONObject fluentClear() {
        map.clear();
        return this;
    }

    public JSONObject fluentRemove(Object key) {
        map.remove(key);
        return this;
    }

    @Override
    public Object clone() {
        return new JSONObject(map instanceof LinkedHashMap //
                ? new LinkedHashMap<String, Object>(map) //
                : new HashMap<String, Object>(map)
        );
    }

    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    private JsonNode getJsonNodeByRootJsonNode(String key) {
        if (jsonNode == null) {
            jsonNode = getJsonNode(this.toString());
        }
        if (jsonNode == null) {
            return null;
        }
        JsonNode jsonNodeForKey = jsonNode.get(key);
        return jsonNodeForKey;
    }


}
