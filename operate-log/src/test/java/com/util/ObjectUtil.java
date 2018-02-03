package com.util;

import org.junit.Test;
import org.springframework.util.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yd on  2017-12-27
 * @Description：
 **/
public class ObjectUtil {

    public static <T> T[] list2Array(List<T> list, Class aClass) {
        T[] array = (T[]) Array.newInstance(aClass, list.size());
        list.toArray(array);
        return array;
    }

    public static <T> T[] list2Array2(List<T> list, Class<T> aClass) {
        Assert.notEmpty(list, "the list must not empty!");
        T[] array = (T[]) Array.newInstance(aClass, list.size());
        for (int i = 0; i < list.size(); i++)
            array[i] = list.get(i);
        return array;
    }

    public static <T> T object2T(Object obj, Class<T> aClass) {//数据类型必须一致
        return (T) obj;
    }

    @Test
    public void test1() {
        List a = new ArrayList();
        a.add("a");
        a.add("b");
//        a.add(123);//类型不一样则报错
        String[] aa = list2Array2(a, String.class);//a.get(0).getClass()
        System.out.println(aa.toString());
    }

    @Test
    public void test2() {
        String a = "aaa";
        String b = object2T(a, String.class);
        System.out.println(b);
    }

}
