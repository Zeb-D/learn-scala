package com.yd.scala.hello.extension.utils;

import com.yd.scala.hello.extension.path.PathReader;


public class PathUtils {
    private final static PathReader READER = new PathReader();

    public static Object getObjectValue(Object obj, String path) {
        return READER.read(obj, path);
    }

    public static String getStringValue(Object obj, String path) {
        Object val = READER.read(obj, path);
        if (val == null) {
            return null;
        }
        return val instanceof String ? (String) val : val.toString();
    }

    public static Boolean getBoolValue(Object obj, String path) {
        Object val = READER.read(obj, path);
        if (val == null) {
            return null;
        }
        return val instanceof Boolean ? (Boolean) val : Boolean.parseBoolean(val.toString());
    }

    public static Short getShortValue(Object obj, String path) {
        Object val = READER.read(obj, path);
        if (val == null) {
            return null;
        }
        return val instanceof Number ? ((Number) val).shortValue() : Short.parseShort(val.toString());
    }

    public static Integer getIntValue(Object obj, String path) {
        Object val = READER.read(obj, path);
        if (val == null) {
            return null;
        }
        return val instanceof Number ? ((Number) val).intValue() : Integer.parseInt(val.toString());
    }

    public static Long getLongValue(Object obj, String path) {
        Object val = READER.read(obj, path);
        if (val == null) {
            return null;
        }
        return val instanceof Number ? ((Number) val).longValue() : Long.parseLong(val.toString());
    }

    public static Float getFloatValue(Object obj, String path) {
        Object val = READER.read(obj, path);
        if (val == null) {
            return null;
        }
        return val instanceof Number ? ((Number) val).floatValue() : Float.parseFloat(val.toString());
    }

    public static Double getDoubleValue(Object obj, String path) {
        Object val = READER.read(obj, path);
        if (val == null) {
            return null;
        }
        return val instanceof Number ? ((Number) val).doubleValue() : Double.parseDouble(val.toString());
    }
}
