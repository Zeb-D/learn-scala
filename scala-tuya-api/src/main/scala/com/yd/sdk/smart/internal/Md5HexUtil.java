package com.yd.sdk.smart.internal;

import org.apache.commons.lang.StringUtils;

public class Md5HexUtil {
    // protected static Logger logger = LoggerFactory.getLogger(Md5HexUtil.class);

    public Md5HexUtil() {
    }

    public static String md5Hex(String data) {
        return md5Hex(data, false);
    }

    public static String md5Hex(String data, boolean isMd5) {
        String hex = "";
        if(StringUtils.isNotBlank(data)) {
            if(!isMd5) {
                hex = MD5Util.getMD5(data);
            }

            StringBuffer sb = new StringBuffer();
            sb.append(hex.substring(8, 16))
                    .append(hex.substring(0, 8))
                    .append(hex.substring(24, 32))
                    .append(hex.substring(16,24));
            hex = sb.toString();
        }
        return hex;
    }

    public static String md5Hex2(String data, boolean isMd5) {
        String hex = "";
        if(StringUtils.isNotBlank(data)) {
            if(!isMd5) {
                hex = MD5Util.getMD5(data);
            }
            String midden = hex.substring(8, 24);
            String temp = midden.substring(0, 8);
            temp = temp + hex.substring(0, 8);
            temp = temp + hex.substring(24, 32);
            temp = temp + midden.substring(8, 16);
            hex = temp;

        }

        return hex;
    }
    public static void main(String [] args){
        String str = "{\"productKey\":\"hDbYlEbRkVkUuj6R\",\"hid\":\"changcheng_ubuntu_test_AA\"}";
        System.err.println(md5Hex(str));
        System.err.println(md5Hex2(str,false));
    }
}
