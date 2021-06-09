package com.yd.http.testing;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Map;

/**
 * 请求工具类
 *
 * @author created by Zeb灬D on 2019-10-26 17:59
 */
public class HttpUtil {
    public static String HOST;
    public static String ProHOST;
    public static String ClientId;
    public static String Secret;
    public static String AccessToken;

    public static void Init(String host, String clientId, String secret) {
        HOST = host;
        ClientId = clientId;
        Secret = secret;
    }

    public static void InitToken(String accessToken) {
        AccessToken = accessToken;
    }

    public static void InitProHOST(String proHOST) {
        ProHOST = proHOST;
    }

    public static String doRequest(String pattern, String method,
                                   Map<String, Object> uriParams,
                                   Map<String, Object> queryParams,
                                   Map<String, Object> bodyParams) {


        return "";
    }

    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    public static String HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    public static String getMD5(String inputStr) {
        if (inputStr == null) return null;
        return getMD5(inputStr.getBytes());
    }

    /**
     * @param source
     * @return 获取md5
     */
    public static String getMD5(byte[] source) {
        String s = null;
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte[] tmp = md.digest();
            char[] str = new char[32];
            int k = 0;

            for (int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            s = new String(str);
            return s;
        } catch (Exception var9) {
            throw new IllegalStateException(var9);
        }
    }

    /**
     * 将加密后的字节数组转换成字符串
     *
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

}
