package com.yd.api.util;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;

/**
 * Created by yd on 2018/08/21
 **/
public class LockPwdUtil {

    public static String decryptAccessKey(String accessKey,String key) {
        if (StringUtils.isBlank(accessKey)) {
            return "";
        }
        String result = "";
        try {
            AESUtil aes = new AESUtil();
            aes.setALGO("AES");
            aes.setKeyValue(key.getBytes());
            result = aes.decrypt(String.valueOf(accessKey));
        } catch (Exception ex) {
        }

        return result;
    }

    public static String encryptAccessKey(String accessKey,String key) {
        if (StringUtils.isBlank(accessKey)) {
            return "";
        }
        String result = "";
        try {
            AESUtil aes = new AESUtil();
            aes.setKeyValue(key.getBytes());
            aes.setALGO("AES");
            result = aes.encrypt(accessKey);
        } catch (Exception ex) {
        }
        return result;
    }



    public static byte[] md5(byte[] by){
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(by);
           byte[] tmp = md.digest();
           return tmp;
       }catch (Exception e){
           return null;
       }
    }
    public static byte[] md5(String str){
        return md5(str.getBytes());
    }
}
