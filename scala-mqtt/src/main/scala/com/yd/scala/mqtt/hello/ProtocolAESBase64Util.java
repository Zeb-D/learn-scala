package com.yd.scala.mqtt.hello;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * 用于AES 加解密
 *
 * @author created by Zeb-D on 2019-08-09 21:36
 */
public class ProtocolAESBase64Util {
    private static final String AES = "AES";
    private String ALGO;
    private byte[] keyValue;

    public String encrypt(String data) throws Exception {
        Key key = new SecretKeySpec(this.keyValue, this.ALGO);
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(1, key);
        byte[] encVal = c.doFinal(StringUtils.getBytesUtf8(data));
        return Base64.encodeBase64String(encVal);
    }

    public String decrypt(String encryptedData) throws Exception {
        Key key = new SecretKeySpec(this.keyValue, this.ALGO);
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(2, key);
        byte[] decodedValue = Base64.decodeBase64(encryptedData);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = StringUtils.newStringUtf8(decValue);
        return decryptedValue;
    }

    public static String decrypt(String data, String secretKey) throws Exception {
        ProtocolAESBase64Util aes = new ProtocolAESBase64Util();
        aes.setALGO("AES");
        aes.setKeyValue(secretKey.getBytes());
        String result = aes.decrypt(data);
        System.out.println("解密data:" + data + "[secretKey]:" + secretKey + "[result]:" + result);
        return aes.decrypt(data);
    }

    public static String encrypt(String data, String secretKey) throws Exception {
        ProtocolAESBase64Util aes = new ProtocolAESBase64Util();
        aes.setALGO("AES");
        aes.setKeyValue(secretKey.getBytes());
        String result = aes.encrypt(data);
        System.out.println("加密data:" + data + "[secretKey]:" + secretKey + "[result]:" + result);
        return aes.encrypt(data);
    }

    public static void main(String[] args) throws Exception {
        String a1 = encrypt("aa1ba24", "12C21o31m34mo2na");
        String a2 = encrypt("aaa", "12C21o31m34mo2na");
        String a3 = encrypt("aa1ba24", "12C21o31m34mo2ac");
        String a4 = encrypt("aaa", "12C21o31m34mo2ac");
        decrypt(a1,"12C21o31m34mo2na");
        decrypt(a2,"12C21o31m34mo2na");
        decrypt(a3,"12C21o31m34mo2ac");
        decrypt(a4,"12C21o31m34mo2ac");
        String s = "eyJkYXRhIjoiOWVaM0ZCK3JpYnplZThpSEUvbUcwVWROb3lISkNWaDFWc3lKMlA5cUZ6VmhFZ1J0QkNuNDhHNlJvNHM2dzkyOHptOTNxWUJwL2s5U2tPcWQ5YTFtUTN3eGxxWlNxUFNlTVB3bVNFQWZOQ3BONSsyejJCR1pjVjN0Wml3YWpMMEs3a2o5VXVhblpnSzVma0xMZnRFQVF4dVYyejhSeldZNTRjMTVyaEQ5bWZ3NmhaSkpBbGtxb09Ba2hXOUlBc2Vwa3pKMURKWDdJV0w0dHgreDJ1SEhnYnR3SXNGSk9Mb3YzRlErbXFUZnZyNVhvdlBNV0FSRFRJaEVRSVFPaDlPZiIsInByb3RvY29sIjo0LCJwdiI6IjIuMCIsInNpZ24iOiJiYjc4ZWQ1NWY5ZmIyZWM3ZmY3N2VhODY5YzdiZGZiZiIsInQiOjE1NzU4NjA2Njg0MTl9";
        byte[] bs = Base64.decodeBase64(s);
        String Value = StringUtils.newStringUtf8(bs);
        System.out.println(Value);
    }

    private Key generateKey() throws Exception {
        return new SecretKeySpec(this.keyValue, this.ALGO);
    }

    public String getALGO() {
        return ALGO;
    }

    public void setALGO(String ALGO) {
        this.ALGO = ALGO;
    }

    public byte[] getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(byte[] keyValue) {
        this.keyValue = keyValue;
    }

}
