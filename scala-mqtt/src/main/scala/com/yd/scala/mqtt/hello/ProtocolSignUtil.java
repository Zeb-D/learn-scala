package com.yd.scala.mqtt.hello;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * 用于sign作用
 *
 * @author created by Zeb-D on 2019-08-09 21:49
 */
public class ProtocolSignUtil {

    public static String getSign(Integer protocol, long time, String pv, String data, String localKey){
        return getMD5(createSignInput(protocol, time, pv, data, localKey));
    }

    /**
     * @param protocol
     * @param time
     * @param pv
     * @param data
     * @param localKey
     * @return 生成sign 规则
     */
    public static String createSignInput(Integer protocol, long time, String pv, String data, String localKey) {
        TreeMap<String, String> params = new TreeMap();
        params.put("protocol", protocol.toString());
        params.put("t", String.valueOf(time));
        params.put("pv", pv);
        if (data != null) {
            params.put("data", data);
        }

        StringBuilder str = new StringBuilder();
        Set<String> keySet = params.keySet();
        Iterator<String> iter = keySet.iterator();

        while (iter.hasNext()) {
            String key = iter.next();
            if (!isBlank(params.get(key))) {
                str.append(key);
                str.append("=");
                str.append(params.get(key));
                str.append("||");
            }
        }

        str.append(localKey);
        return str.toString();
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String getMD5(String inputStr) {
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

    public static void main(String[] args) {
        System.out.println(getMD5("a").length());
        System.out.println(getSign(5,1565787443703L,"2.0","NEaWjqI+RUXbVmL0z37/hGO0vuCHGSFiec4/WiNu49ce9yBJCawaWHQIxI2QC/8/8hfmhwhVRNm04/3oBazGI7MR2XgIWDbCHWsk4rO1ufMP+bAoFhPqkY4FbH49gwTB+Dc2IljEIu+fykTCv1dTIw==",
                "jvvesfswkx9ffsahurm5q7umgxp7e75w".substring(8,24)).substring(8,24));
        System.out.println(new SimpleDateFormat("hhmmss:sss").format(new Date(1565791106193L)));
    }
}
