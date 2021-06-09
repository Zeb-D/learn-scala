package com.yd.scala.hello;

import java.security.SecureRandom;
import java.text.ParseException;

public class SessionHelper {
    private SessionHelper() {
    }

    private static final SecureRandom random = new SecureRandom();

    private static String getRandomString(String baseString, int length) {
        int baseStringLength = baseString.length();
        StringBuilder rt = new StringBuilder(length);

        for (int i = 0; i < length; ++i) {
            int rint = random.nextInt(baseStringLength);
            rt.append(baseString.charAt(rint));
        }

        return rt.toString();
    }


    public static String getRandomString(int length) {
        String all = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
        return getRandomString(all, length);
    }

    public static String createUidStr(String uid) {
        String str = "";
        float ff = (float) uid.length() / 8.0F;
        int div = (int) Math.ceil((double) ff);
        Integer start = 0;

        for (int i = 0; i < div; ++i) {
            Integer end = (i + 1) * 8;
            if (end > uid.length()) {
                end = uid.length();
            }

            str = str + uid.substring(start, end);
            str = str + getRandomString(1);
            start = end;
        }

        str = str + div;
        return str;
    }

    public static String createSession(String uid, String deviceIdMd5) {
        String uidStr = createUidStr(uid);
        String newString = uidStr + deviceIdMd5;
        return newString;
    }

    public static String getUidBySessionId(String sessionId) {
        String str = sessionId.substring(0, sessionId.length() - 32);
        String str1 = str.substring(0, str.length() - 1);
        Integer str2 = Integer.valueOf(str.substring(str.length() - 1, str.length()));
        Integer uidLength = str1.length() - str2;
        float ff = (float) uidLength / 8.0F;
        int div = (int) Math.ceil((double) ff);
        Integer start = 0;
        String str3 = "";

        for (int i = 0; i < div; ++i) {
            Integer end = (i + 1) * 8;
            if (end > uidLength) {
                end = uidLength;
            }

            str3 = str3 + str1.substring(start, end + i);
            start = end + i + 1;
        }

        return str3;
    }

    public static void main(String[] args) throws ParseException {
        String uid = "00014188121234567891";
        System.out.println("lenth:" + uid.length());
        String uid2 = createUidStr(uid);
        System.out.println(uid2);
        String uid3 = createSession(uid, "25d55ad283aa400af464c76d713c07ad");
        System.out.println(uid3);
        String str3 = getUidBySessionId(uid3);
        System.out.println(str3);
    }
}