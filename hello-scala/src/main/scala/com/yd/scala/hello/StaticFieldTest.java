package com.yd.scala.hello;

/**
 * @author created by Zeb灬D on 2022-01-06 11:31
 */
public class StaticFieldTest {
    private static StaticFieldTest pending = null;
    StaticFieldTest next;
    Object ref;

    public static void main(String[] args) {
        char c = (char) 0xff;
        byte b = -1;
        char d = (char) (2);
        System.out.println(c);
        System.out.println(d);
        StaticFieldTest t1 = new StaticFieldTest();
        Object o1 = new Object();
        t1.ref = o1;
        StaticFieldTest t2 = new StaticFieldTest();
        Object o2 = new Object();
        t2.ref = o2;
        t2.next = t1;

        pending = t2;
        System.out.println(pending);
        System.gc();

        int radix = 1 << 4;
        System.out.println(radix);
        System.out.println(10 & radix);
        System.out.println(digits[10 & radix]);
    }

    final static char[] digits = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };
}
