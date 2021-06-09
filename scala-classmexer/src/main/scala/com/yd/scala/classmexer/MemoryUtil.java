package com.yd.scala.classmexer;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author created by Zeb灬D on 2020-07-31 20:50
 */
public class MemoryUtil {
    public static long memoryUsageOf(Object obj) {
        return Agent.getInstrumentation().getObjectSize(obj);
    }

    public static long deepMemoryUsageOf(Object obj) {
        return deepMemoryUsageOf(obj, MemoryUtil.VisibilityFilter.NON_PUBLIC);
    }

    public static long deepMemoryUsageOf(Object obj, MemoryUtil.VisibilityFilter referenceFilter) {
        return deepMemoryUsageOf0(Agent.getInstrumentation(), new HashSet(), obj, referenceFilter);
    }

    public static long deepMemoryUsageOfAll(Collection<? extends Object> objs) {
        return deepMemoryUsageOfAll(objs, MemoryUtil.VisibilityFilter.NON_PUBLIC);
    }

    public static long deepMemoryUsageOfAll(Collection<? extends Object> objs, MemoryUtil.VisibilityFilter referenceFilter) {
        Instrumentation instr = Agent.getInstrumentation();
        long total = 0L;
        Set<Integer> counted = new HashSet(objs.size() * 4);

        Object o;
        for (Iterator var7 = objs.iterator(); var7.hasNext(); total += deepMemoryUsageOf0(instr, counted, o, referenceFilter)) {
            o = var7.next();
        }

        return total;
    }

    private static long deepMemoryUsageOf0(Instrumentation instrumentation, Set<Integer> counted,
                                           Object obj, MemoryUtil.VisibilityFilter filter) throws SecurityException {
        Stack<Object> st = new Stack();
        st.push(obj);
        long total = 0L;

        while (true) {
            Object o;
            do {
                if (st.isEmpty()) {
                    return total;
                }

                o = st.pop();
            } while (!counted.add(System.identityHashCode(o)));

            long sz = instrumentation.getObjectSize(o);
            total += sz;
            Class clz = o.getClass();
            Class compType = clz.getComponentType();
            int var14;
            if (compType != null && !compType.isPrimitive()) {
                Object[] arr = (Object[]) o;
                Object[] var16 = arr;
                var14 = 0;

                for (int var15 = arr.length; var14 < var15; ++var14) {
                    Object el = var16[var14];
                    if (el != null) {
                        st.push(el);
                    }
                }
            }

            while (clz != null) {
                Field[] var22 = clz.getDeclaredFields();
                int var21 = 0;

                for (var14 = var22.length; var21 < var14; ++var21) {
                    Field fld = var22[var21];
                    int mod = fld.getModifiers();
                    if ((mod & 8) == 0 && isOf(filter, mod)) {
                        Class fieldClass = fld.getType();
                        if (!fieldClass.isPrimitive()) {
                            if (!fld.isAccessible()) {
                                fld.setAccessible(true);
                            }

                            try {
                                Object subObj = fld.get(o);
                                if (subObj != null) {
                                    st.push(subObj);
                                }
                            } catch (IllegalAccessException var19) {
                                throw new InternalError("Couldn't read " + fld);
                            }
                        }
                    }
                }

                clz = clz.getSuperclass();
            }
        }
    }

    private static boolean isOf(MemoryUtil.VisibilityFilter f, int mod) {
        switch (f.ordinal()) {
            case 1:
                return true;
            case 2:
                if ((mod & 2) != 0) {
                    return true;
                }

                return false;
            case 3:
                if ((mod & 1) == 0) {
                    return true;
                }

                return false;
            case 4:
                return false;
            default:
                throw new IllegalArgumentException("Illegal filter " + mod);
        }
    }

    private MemoryUtil() {
    }

    public static enum VisibilityFilter {
        ALL,
        PRIVATE_ONLY,
        NON_PUBLIC,
        NONE;

        private VisibilityFilter() {
        }
    }
}
