package com.yd.sdk.smart.util;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class RandomUtil {
    private static final SecureRandom random = new SecureRandom();

    public RandomUtil() {
    }

    public static int getInt(int min, int max) {
        return min + random.nextInt(max - min);
    }

    public static String getRandomAlpha(int length) {
        String all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return getRandomString(all, length);
    }

    public static String getRandomString(int length) {
        String all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
        return getRandomString(all, length);
    }

    public static String getRandomCouponCode(int length) {
        String all = "abcdefghjkmnpqrstuvwxyzACDEFGHJKMNPQRSTUVWXYZ2345679";
        return getRandomString(all, length);
    }

    public static String getRandomCouponCodeInUpperCase(int length) {
        String all = "ACDEFGHJKLMNPQRSTUVWXYZ0123456789";
        return getRandomString(all, length);
    }

    public static String getRandomStringInLowerCase(int length) {
        String all = "acdefghjkmnpqrstuvwxy345789";
        return getRandomString(all, length);
    }

    public static String getNumerialRandomCode(int length) {
        String all = "0123456789";
        return getRandomString(all, length);
    }

    private static String getRandomString(String baseString, int length) {
        int baseStringLength = baseString.length();
        StringBuilder rt = new StringBuilder(length);

        for(int i = 0; i < length; ++i) {
            int rint = random.nextInt(baseStringLength);
            rt.append(baseString.charAt(rint));
        }

        return rt.toString();
    }

    public static String getRandomByMod(String string, int length) {
        if (length > string.length()) {
            length = string.length();
        }

        StringBuilder str = new StringBuilder(length);

        for(int i = 0; i < length; ++i) {
            int m = string.charAt(i) % length;
            str.append(string.charAt(m));
        }

        return str.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String abc = getRandomByMod("sd", 5);
        System.out.println(abc);
    }
}
