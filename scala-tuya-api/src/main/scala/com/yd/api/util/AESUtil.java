package com.yd.api.util;

import com.yd.api.exception.TuyaClientException;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * AES加密使用
 *
 * @date: 2019-03-07 17:19
 */
public class AESUtil {
    private static final String AES = "AES";
    private static final String AES_CBC_NO_PADDING = "AES/CBC/NoPadding";
    private String ALGO;
    private byte[] keyValue;

    public AESUtil() {
    }

    public String encrypt(String Data) throws Exception {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(1, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = byte2hex(encVal);
        return encryptedValue;
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(2, key);
        byte[] decordedValue = hex2byte(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    public static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        } else {
            int l = strhex.length();
            if (l % 2 == 1) {
                return null;
            } else {
                byte[] b = new byte[l / 2];

                for(int i = 0; i != l / 2; ++i) {
                    b[i] = (byte)Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
                }

                return b;
            }
        }
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for(int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }

        return hs.toUpperCase();
    }

    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(this.keyValue, this.ALGO);
        return key;
    }

    public String getALGO() {
        return this.ALGO;
    }

    public void setALGO(String aLGO) {
        this.ALGO = aLGO;
    }

    public byte[] getKeyValue() {
        return this.keyValue;
    }

    public void setKeyValue(byte[] keyValue) {
        this.keyValue = keyValue;
    }

    public static byte[] aesCbcNoPaddingEncrypt(byte[] data, byte[] key, byte[] aesIV) throws Exception {
        int len;
        for(len = data.length; len % 16 != 0; ++len) {
        }

        byte[] result = new byte[len];

        for(int i = 0; i < len; ++i) {
            if (i < data.length) {
                result[i] = data[i];
            } else {
                result[i] = 0;
            }
        }

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesIV);
        Cipher cipher = null;

        try {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(1, skeySpec, iv);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return cipher.doFinal(result);
    }

    public static byte[] aesCbcNoPaddingDecrypt(byte[] data, byte[] key, byte[] aesIV) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec iv = new IvParameterSpec(aesIV);
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(2, skeySpec, iv);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {

    }
}
