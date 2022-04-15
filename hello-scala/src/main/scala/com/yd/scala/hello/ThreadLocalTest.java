package com.yd.scala.hello;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author created by Zeb灬D on 2021-08-18 19:46
 */
public class ThreadLocalTest {
    private static final ThreadLocal<String> tl = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        tl.set("aaaa");
        new Thread(() -> {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + "->" + tl.get());
            try {
                Field field = t.getClass().getDeclaredField("threadLocals");
                field.setAccessible(true);
                tl.set("123");
                //field.set(t, null); //ThreadLocal 真的remove了吗
                Object threadLocalMap = field.get(t);
                System.out.println("1threadLocalMap -->"+ JSONObject.toJSONString(threadLocalMap));
                Class<?> tlmClass = threadLocalMap.getClass();
                Method m = tlmClass.getDeclaredMethod("remove", ThreadLocal.class);
                //remove
                m.setAccessible(true);
                m.invoke(threadLocalMap, tl);
                System.out.println("2threadLocalMap -->"+ JSONObject.toJSONString(threadLocalMap));
                System.out.println("123 ?? ->" + tl.get());
                Field tableField = tlmClass.getDeclaredField("table");
                tableField.setAccessible(true);
                Object[] arr = (Object[]) tableField.get(threadLocalMap);
                for (Object o : arr) {
                    if (o != null) {
                        Class<?> entryClass = o.getClass();
                        Field valueField = entryClass.getDeclaredField("value");
                        Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                        valueField.setAccessible(true);
                        referenceField.setAccessible(true);
                        System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(tl.get());
    }

    static class TL<T> extends ThreadLocal<T> {

    }
}
