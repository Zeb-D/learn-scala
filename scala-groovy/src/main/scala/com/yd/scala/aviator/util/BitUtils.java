package com.yd.scala.aviator.util;

public class BitUtils {
    public BitUtils() {
    }

    public static long pow2(int num) {
        return Double.valueOf(Math.pow(2.0D, (double) num)).longValue();
    }

    public static boolean isTrue(long number, int position) {
        long decimalValue = pow2(position);
        return (decimalValue & number) == decimalValue;
    }

    public static long computeOR(long m, long n) {
        return m | n;
    }

    public static long addBit(long number, int position) {
        return number >= 0L && position >= 0 ? computeOR(number, pow2(position)) : 0L;
    }

    public static long removeBit(long number, int position) {
        if (number >= 0L && position >= 0) {
            if (isTrue(number, position)) {
                number -= pow2(position);
            }

            return number;
        } else {
            return 0L;
        }
    }

    public static int powInt2(int num) {
        return Double.valueOf(Math.pow(2.0D, (double) num)).intValue();
    }

    public static boolean isIntTrue(int number, int position) {
        int decimalValue = powInt2(position);
        return (decimalValue & number) == decimalValue;
    }

    public static int computeIntOR(int m, int n) {
        return m | n;
    }

    public static int addIntBit(int number, int position) {
        return number >= 0 && position >= 0 ? computeIntOR(number, powInt2(position)) : 0;
    }

    public static int removeIntBit(int number, int position) {
        if (number >= 0 && position >= 0) {
            if (isTrue((long) number, position)) {
                number -= powInt2(position);
            }

            return number;
        } else {
            return 0;
        }
    }

    public static boolean isTrue(long number, long position) {
        long decimalValue = pow2(position);
        return (decimalValue & number) == decimalValue;
    }

    public static long pow2(long num) {
        return Double.valueOf(Math.pow(2.0D, (double) num)).longValue();
    }

    public static void main(String[] args) {
        int aa = addIntBit(256, 9);
        aa = addIntBit(aa, 0);
        aa = addIntBit(aa, 1);
        System.out.println("aa=" + aa);
        boolean t = isTrue((long) aa, 0);
        if (t) {
            System.out.println("0位");
        }

        t = isTrue((long) aa, 1);
        if (t) {
            System.out.println("1位");
        }

        t = isTrue((long) aa, 9);
        if (t) {
            System.out.println("9位");
        }

        t = isTrue((long) aa, 8);
        if (t) {
            System.out.println("8位");
        }

        t = isTrue((long) aa, 20);
        if (t) {
            System.out.println("8位");
        }

    }
}