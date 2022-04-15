package com.yd.scala.hello.extension.invoker;

import com.yd.scala.hello.extension.config.Script;
import com.yd.scala.hello.extension.path.PathReader;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;


public class ParameterCalculate {
    private final static Object NULL_OBJECT = new Object();
    private final Object object;
    private final PathReader pathReader;
    private final Map<String, Object> pathValueMap = new HashMap<>();

    public ParameterCalculate(Object obj, PathReader pathReader) {
        this.object = obj;
        this.pathReader = pathReader;
    }

    public Object getValue(String path) {
        Object value = pathValueMap.get(path);
        if (value != null) {
            return value == NULL_OBJECT ? null : value;
        }
        value = pathReader.read(object, path);
        pathValueMap.put(path, value == null ? NULL_OBJECT : value);
        return value;
    }

    public boolean match(Script script) {
        //前期只支持参数读取，type == "object_path_value"
        if (!"object_path_value".equals(script.getType())) {
            return false;
        }
        Object value = getValue(script.getScript());
        return match(value, script.getOperator(), script.getValues());
    }

    public boolean match(Object leftValue, String operator, Object[] rightValues) {
        switch (operator.toUpperCase()) {
            case "IS_NULL":
                return leftValue == null;
            case "NOT_NULL":
                return leftValue != null;
            case "EQU":
            case "=":
            case "==":
                return checkLeftAndOneRightValue(leftValue, rightValues) && OperatorEQU(leftValue, rightValues[0]);
            case "NEQ":
            case "!=":
            case "!==":
                return checkLeftAndOneRightValue(leftValue, rightValues) && !OperatorEQU(leftValue, rightValues[0]);
            case "LSS":
            case "<":
                return checkLeftAndOneRightValue(leftValue, rightValues) && OperatorLSS(leftValue, rightValues[0]);
            case "LEQ":
            case "<=":
                return checkLeftAndOneRightValue(leftValue, rightValues) && OperatorLEQ(leftValue, rightValues[0]);
            case "GTR":
            case ">":
                return checkLeftAndOneRightValue(leftValue, rightValues) && OperatorGTR(leftValue, rightValues[0]);
            case "GEQ":
            case ">=":
                return checkLeftAndOneRightValue(leftValue, rightValues) && OperatorGEQ(leftValue, rightValues[0]);
            case "BTW":
                return checkLeftAndTwoRightValue(leftValue, rightValues) && OperatorGEQ(leftValue, rightValues[0]) && OperatorLEQ(leftValue, rightValues[1]);
            case "IN":
                return checkLeftAndOneRightValue(leftValue, rightValues) && OperatorIN(leftValue, rightValues);
            case "NOT_IN":
                return checkLeftAndOneRightValue(leftValue, rightValues) && !OperatorIN(leftValue, rightValues);
            default:
                return false;
        }
    }

    private boolean checkLeftAndOneRightValue(Object left, Object[] values) {
        return left != null && values != null && values.length >= 1 && values[0] != null;
    }

    private boolean checkLeftAndTwoRightValue(Object left, Object[] values) {
        return left != null && values != null && values.length >= 2 && values[0] != null && values[1] != null;
    }

    private static boolean canCastDouble(Object value) {
        return value instanceof Character || (((value instanceof Number) &&
                (value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long ||
                        value instanceof Float || value instanceof Double)));
    }


    private static boolean OperatorEQU(Object v1, Object v2) {
        if (stringCompare("EQU", v1, v2)) {
            return Boolean.TRUE;
        }
        if (canCastDouble(v1) && canCastDouble(v2)) {
            return Double.doubleToLongBits(convertDouble(v1)) == Double.doubleToLongBits(convertDouble(v2));
        }
        return Boolean.FALSE;
    }

    private static boolean OperatorEQUV2(Object v1, Object v2) {
        if (v1 == v2 || v2.equals(v1)) {
            return true;
        }
        if (v1 == null || v1 instanceof Double || v1 instanceof Float) {
            return false;
        }
        if (v1 instanceof Byte || v1 instanceof Short || v1 instanceof Integer || v1 instanceof Long) {
            return ((v2 instanceof Number) && ((Number) v1).longValue() == ((Number) v2).longValue())
                    || ((v2 instanceof Character) && ((Number) v1).intValue() == (Character) v2);
        }
        if (v1 instanceof Character) {
            return (v2 instanceof Number) && (Character) v1 == ((Number) v2).intValue();
        }
        return false;
    }

    private static boolean OperatorLSS(Object v1, Object v2) {
        if (stringCompare("LSS", v1, v2)) {
            return Boolean.TRUE;
        }
        if (canCastDouble(v1) && canCastDouble(v2)) {
            return Double.doubleToLongBits(convertDouble(v1)) < Double.doubleToLongBits(convertDouble(v2));
        }
        return Boolean.FALSE;
    }

    private static boolean OperatorLEQ(Object v1, Object v2) {
        if (stringCompare("LEQ", v1, v2)) {
            return Boolean.TRUE;
        }
        if (canCastDouble(v1) && canCastDouble(v2)) {
            return Double.doubleToLongBits(convertDouble(v1)) <= Double.doubleToLongBits(convertDouble(v2));
        }
        return Boolean.FALSE;
    }

    private static boolean OperatorGTR(Object v1, Object v2) {
        if (stringCompare("GTR", v1, v2)) {
            return Boolean.TRUE;
        }
        if (canCastDouble(v1) && canCastDouble(v2)) {
            return Double.doubleToLongBits(convertDouble(v1)) > Double.doubleToLongBits(convertDouble(v2));
        }
        return Boolean.FALSE;
    }

    private static boolean OperatorGEQ(Object v1, Object v2) {
        if (stringCompare("GEQ", v1, v2)) {
            return Boolean.TRUE;
        }
        if (canCastDouble(v1) && canCastDouble(v2)) {
            return Double.doubleToLongBits(convertDouble(v1)) >= Double.doubleToLongBits(convertDouble(v2));
        }
        return Boolean.FALSE;
    }

    private boolean OperatorIN(Object v1, Object[] v2) {
        for (Object v : v2) {
            if (OperatorEQU(v1, v)) {
                return true;
            }
        }
        return false;
    }

    private static double convertDouble(Object value) {
        if (value instanceof Character) {
            Character character = (Character) value;
            int charValue = character.charValue();
            return charValue;
        }
        return ((Number) value).doubleValue();
    }

    private static boolean stringCompare(String operator, Object v1, Object v2) {
        if (v1 instanceof String && v2 instanceof String) {
            switch (operator) {
                case "LSS":
                    return ((String) v1).compareTo((String) v2) < 0;
                case "GTR":
                    return ((String) v1).compareTo((String) v2) > 0;
                case "LEQ":
                    return ((String) v1).compareTo((String) v2) <= 0;
                case "GEQ":
                    return ((String) v1).compareTo((String) v2) >= 0;
                case "EQU":
                    return ((String) v1).compareTo((String) v2) == 0;
                default:
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // ==
        Assert.isTrue(!OperatorEQU(100, 10.0f));
        Assert.isTrue(!OperatorEQU(1, 1.1d));
        Assert.isTrue(!OperatorEQU(5, 'a'));
        Assert.isTrue(OperatorEQU("a", "a"));
        Assert.isTrue(OperatorEQU(1, 1.0f));
        Assert.isTrue(OperatorEQU(1, new Short((short) 1)));
        Assert.isTrue(OperatorEQU('a', 97));

        // <
        Assert.isTrue(OperatorLSS(10, 100));
        Assert.isTrue(!OperatorLSS(10, new Short((short) 5)));
        Assert.isTrue(OperatorLSS(10, 10.1f));
        Assert.isTrue(OperatorLSS(10, 10.1d));
        Assert.isTrue(OperatorLSS(10, 'a'));
        Assert.isTrue(OperatorLSS(10, 100L));
        Assert.isTrue(!OperatorLSS(10, new Byte((byte) 5)));
        Assert.isTrue(OperatorLSS("a", "b"));

        // >
        Assert.isTrue(!OperatorGTR(10, 100));
        Assert.isTrue(OperatorGTR(10, new Short((short) 5)));
        Assert.isTrue(!OperatorGTR(10, 10.1f));
        Assert.isTrue(!OperatorGTR(10, 10.1d));
        Assert.isTrue(!OperatorGTR(10, 'a'));
        Assert.isTrue(!OperatorGTR(10, 100L));
        Assert.isTrue(OperatorGTR(10, new Byte((byte) 5)));
        Assert.isTrue(!OperatorGTR("a", "b"));

        // >=
        Assert.isTrue(OperatorGEQ(100, 100));
        Assert.isTrue(OperatorGEQ(10, new Short((short) 10)));
        Assert.isTrue(!OperatorGEQ(10, 10.1f));
        Assert.isTrue(!OperatorGEQ(10, 10.1d));
        Assert.isTrue(OperatorGEQ(97, 'a'));
        Assert.isTrue(!OperatorGEQ(10, 100L));
        Assert.isTrue(OperatorGEQ(10, new Byte((byte) 5)));
        Assert.isTrue(OperatorGEQ("a", "a"));

        // <=
        Assert.isTrue(OperatorLEQ(100, 100));
        Assert.isTrue(OperatorLEQ(10, new Short((short) 10)));
        Assert.isTrue(OperatorLEQ(10, 10.1f));
        Assert.isTrue(OperatorLEQ(10, 10.1d));
        Assert.isTrue(!OperatorLEQ(97.1, 'a'));
        Assert.isTrue(OperatorLEQ(10, 100L));
        Assert.isTrue(!OperatorLEQ(10, new Byte((byte) 5)));
        Assert.isTrue(OperatorLEQ("a", "b"));

    }


//    public static void YdaConfig(String[] args) {
//        long start = System.currentTimeMillis();
//        Double aDouble = new Double("11");
//        long a = 1000L;
//        Boolean b = false;
//
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 100000000; i++) {
//            if (a == i) {
//                b = true;
//            }
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
//
//        a = 1000;
//        b = false;
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 100000000; i++) {
//            if (OperatorEQUV2(i, a)) {
//                b = true;
//            }
//        }
//
//        System.out.println(System.currentTimeMillis() - start);
//
//        a = 1000;
//        b = false;
//        start = System.currentTimeMillis();
//        for (int i = 0; i < 100000000; i++) {
//            if (OperatorEQU(i, a)) {
//                b = true;
//            }
//        }
//
//        System.out.println(System.currentTimeMillis() - start);

//        long start = System.currentTimeMillis();
//        Object value = 1;
//        for (int i = 0; i < 1000000000; i++) {
//           boolean b =  value instanceof Character || (((value instanceof Number) &&
//                    (value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long ||
//                            value instanceof Float || value instanceof Double)));
//        }
//        System.out.println(System.currentTimeMillis() - start);
//
//    }
}
