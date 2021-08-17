package com.yd.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class JSONArray extends JSON implements List<Object>, Cloneable, RandomAccess, Serializable {

    private final List<Object> list;

    private JsonNode jsonNode;

    public JSONArray(){
        this.list = new ArrayList<Object>();
    }

    public JSONArray(List<Object> list){
        if (list == null){
            throw new IllegalArgumentException("list is null.");
        }
        this.list = list;
    }

    public JSONArray(int initialCapacity){
        this.list = new ArrayList<Object>(initialCapacity);
    }

    public void setJsonNode(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    public static JSONArray parseJSONArray(Object value) {
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
        JSONArray jsonArray = toJSONArray(value);
        return jsonArray;
    }

    public JSONArray getJSONArray(int index) {
        Object value = list.get(index);
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
        JSONArray jsonArray = toJSONArray(value);
        return jsonArray;
    }

    public JSONObject getJSONObject(int index) {
        Object value = list.get(index);
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
        return toGivenObject(value, JSONObject.class);
    }

    public <T> T getObject(int index, Class<T> clazz) {
        Object value = list.get(index);
        return toGivenObject(value, clazz);
    }

    public <T> T getObject(int index, JavaType type) {
        Object value = list.get(index);
        return toGivenObject(value, type);
    }

    public Boolean getBoolean(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
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

    public boolean getBooleanValue(int index) {
        Boolean value = getBoolean(index);
        if (null == value) {
            return false;
        }
        return value.booleanValue();
    }

    public byte[] getBytes(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
        if (jsonNodeForKey == null) {
            return null;
        }
        byte[] bytes;
        ObjectNode objectNode;
        try {
            bytes = jsonNodeForKey.binaryValue();
        } catch (IOException e) {
            throw new JSONException("can not cast to byte[]");
        }
        return bytes;
    }

    public Short getShort(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
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

    public short getShortValue(int index) {
        Short value = getShort(index);
        if (null == value) {
            return 0;
        }
        return value.shortValue();
    }

    public Integer getInteger(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
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

    public int getIntValue(int index) {
        Integer value = getInteger(index);
        if (null == value) {
            return 0;
        }
        return value.intValue();
    }

    public Long getLong(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
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

    public long getLongValue(int index) {
        Long value = getLong(index);
        if (null == value) {
            return 0;
        }
        return value.longValue();
    }

    public Float getFloat(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
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

    public float getFloatValue(int index) {
        Float value = getFloat(index);
        if (null == value) {
            return 0;
        }
        return value.floatValue();
    }

    public Double getDouble(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
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

    public double getDoubleValue(int index) {
        Double value = getDouble(index);
        if (null == value) {
            return 0;
        }
        return value.doubleValue();
    }

    public BigDecimal getBigDecimal(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isBigDecimal()) {
            return jsonNodeForKey.decimalValue();
        }
        try {
            Double doubleValue = getDouble(index);
            return BigDecimal.valueOf(doubleValue);
        } catch (Exception e) {
        }
        try {
            Long longValue = getLong(index);
            return BigDecimal.valueOf(longValue);
        } catch (Exception e) {
        }
        throw new JSONException("can not cast to bigDecimal");
    }

    public BigInteger getBigInteger(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isBigInteger()) {
            return jsonNodeForKey.bigIntegerValue();
        }
        try {
            Long longValue = getLong(index);
            return BigInteger.valueOf(longValue);
        } catch (Exception e) {
        }
        throw new JSONException("can not cast to bigInteger");
    }

    public String getString(int index) {
        JsonNode jsonNodeForKey = getJsonNodeByRootJsonNode(index);
        if (jsonNodeForKey == null) {
            return null;
        }
        if (jsonNodeForKey.isTextual()) {
            return jsonNodeForKey.asText();
        }
        return jsonNodeForKey.toString();
    }

    public <T> List<T> toJavaList(Class<T> clazz) {
        return parseArray(toJSONString(this), clazz);
    }


    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<Object> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(Object o) {
        return list.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<?> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<?> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Object get(int index) {
        return list.get(index);
    }

    @Override
    public Object set(int index, Object element) {
        if (index == -1) {
            list.add(element);
            return null;
        }

        if (list.size() <= index) {
            for (int i = list.size(); i < index; ++i) {
                list.add(null);
            }
            list.add(element);
            return null;
        }

        return list.set(index, element);
    }

    @Override
    public void add(int index, Object element) {
        list.add(index, element);
    }

    @Override
    public Object remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<Object> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<Object> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<Object> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }


    public JSONArray fluentAdd(Object e) {
        this.list.add(e);
        return this;
    }

    public JSONArray fluentRemove(Object o) {
        this.list.remove(o);
        return this;
    }

    public JSONArray fluentAddAll(Collection<? extends Object> c) {
        this.list.addAll(c);
        return this;
    }

    public JSONArray fluentAddAll(int index, Collection<? extends Object> c) {
        this.list.addAll(index, c);
        return this;
    }

    public JSONArray fluentRemoveAll(Collection<?> c) {
        this.list.removeAll(c);
        return this;
    }

    public JSONArray fluentRetainAll(Collection<?> c) {
        this.list.retainAll(c);
        return this;
    }

    public JSONArray fluentClear() {
        this.list.clear();
        return this;
    }

    private JsonNode getJsonNodeByRootJsonNode(int index) {
        if (jsonNode == null) {
            jsonNode = getJsonNode(this.toString());
        }
        if (jsonNode == null) {
            return null;
        }
        if (!jsonNode.isArray()) {
            throw new JSONException("can not cast to array");
        }
        JsonNode jsonNodeForKey = jsonNode.get(index);
        return jsonNodeForKey;
    }



}
