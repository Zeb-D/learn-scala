package com.yd.scala.mqtt.hello;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

/**
 * 消息接受器
 * {"data":"OOD4fIgo0RnGdPr3mqdz/o6HsMui4YDLn8BnvmjKZxQNl8BbqfRr08q/iaa1yUYqwlAgaVL21O3C/sy2sW+fsioqnEn53samalyxZqSuMRhd9tLfhyHeTxjJIuCIcR4VFHsBqvbz5mzchs0ilCNXPl6Ij0cDHQd/zu6UQw1iIh1HN9g5LEm2jdE5wlbM0w+kHzliLu030OXaqb75UlgPJJBlpGrqCGSjUBmXAksVSf4O3G5D5y3fLFnAR6j3hxUKFTrlPDbWdJ+BJIfIXE77NA==",
 * "protocol":4,
 * "pv":"2.0",
 * "sign":"1b3301a0fca3640d4ca31c3d5ec4a318",
 * "t":1565774588602}
 *
 * @author created by Zeb-D on 2019-08-09 21:08
 */
public class MessageReceive {
    private static final String TOKEN = "230fbf4293464106f23c2a7879b6facb";
    private static final String SECRET = "4d7753f255767cae4e59e1c36d41548c";//"jvvesfswkx9ffsahurm5q7umgxp7e75w";//"ujkx8f4jvmaaxhdg3jpqtsn57tw4jhy8";//"dt8y49vekn5fchsgg3cr3tff7s4gp84q";//"jvvesfswkx9ffsahurm5q7umgxp7e75w";
    private static final String PAYLOAd_PROTOCOL = "protocol";
    private static final String PAYLOAd_DEVID = "devId";
    private static final String PAYLOAd_T = "t";
    private static final String PAYLOAd_PV = "pv";
    private static final String PAYLOAd_TOKEN = "token";
    private static final String PAYLOAd_DATA = "data";
    private static final String PAYLOAd_SIGN = "sign";
    private static final String DATA_BIZ_CODE = "bizCode";
    private static final String DATA_BIZ_DATA = "bizData";
    private static final String DATA_BIZ_DATA_CODE = "code";
    private static final String DATA_BIZ_DATA_VALUE = "value";

    public static JSONObject receviePayload(String context) {
        if (context == null || context.length() == 0) {
            return null;
        }

        return JSONObject.parseObject(context);
    }

    public static JSONObject recevieData(JSONObject payload) throws Exception {
        String signed = ProtocolSignUtil.getSign(payload.getInteger(PAYLOAd_PROTOCOL),
                payload.getLong(PAYLOAd_T),
                payload.getString(PAYLOAd_PV),
                payload.getString(PAYLOAd_DATA),
                SECRET);
        System.out.println("signed:" + signed + " \n payload-sign:" + payload.getString(PAYLOAd_SIGN));
        String data = ProtocolAESBase64Util.decrypt(payload.getString(PAYLOAd_DATA), SECRET.substring(8, 24));
        System.out.println("data:" + data + "\n d1" + payload.getString(PAYLOAd_DATA));
        return JSONObject.parseObject(data);
    }

    public static byte[] toByteArray(String hexString) {
        if (hexString == null || hexString.length() == 0)
            throw new IllegalArgumentException("this hexString must not be empty");

        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    /**
     * 字节数组转成16进制表示格式的字符串
     *
     * @param byteArray 需要转换的字节数组
     * @return 16进制表示格式的字符串
     **/
    public static String toHexString(byte[] byteArray) {
        if (byteArray == null || byteArray.length < 1)
            throw new IllegalArgumentException("this byteArray must not be null or empty");

        final StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
                hexString.append("0");
            hexString.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return hexString.toString().toLowerCase();
    }

    public static void main(String[] args) throws Exception {
//        String context = "{\"data\":\"9eZ3FB+ribzee8iHE/mG0UdNoyHJCVh1VsyJ2P9qFzVhEgRtBCn48G6Ro4s6w928zm93qYBp/k9SkOqd9a1mQ3wxlqZSqPSeMPwmSEAfNCpN5+2z2BGZcV3tZiwajL0K7kj9UuanZgK5fkLLftEAQxuV2z8RzWY54c15rhD9mfw6hZJJAlkqoOAkhW9IAsepkzJ1DJX7IWL4tx+x2uHHgbtwIsFJOLov3FQ+mqTfvr5XovPMWARDTIhEQIQOh9Of\",\"protocol\":4,\"pv\":\"2.0\",\"sign\":\"bb78ed55f9fb2ec7ff77ea869c7bdfbf\",\"t\":1575860668419}";
//        JSONObject Payload = receviePayload(context);
//        JSONObject data = recevieData(Payload);

        String data1 = "eyJkYXRhIjoiUFFzNDd3QWtXRy91eFJpV21SMFE0NUJRbmcxYlQ4Ynd0M3JaeUlVQXFkU3RzOEQxN242dW9VaUJrUU9CQTF6VVkvWEx2aUNTZGVIYk16ZW5kdVdpei9UWGNNUmZITVRzc3ZubFN1TUx1dzFDbmxST0ErRDQ2cTVWVkNEbTlvenMrVzF2d2ZIQU82cHJvekxpdUY2MXpJaElUaW9JL2QwV2RseGtFRHluU1diRGNCcXJpSWVVdjhQVW1wbTJlYXAzSzB2WGdzSkE1a1hBaFZXL1plWGs5eHZrNUd2Z1h4OVE5ejBEakh2Um9WdFFJQTRoUVJvbGkyL1pYYlRIdWNiUiIsInByb3RvY29sIjo0LCJwdiI6IjIuMCIsInNpZ24iOiIwMDRhZWU5NTIyZDU4NTY0MzRhN2NhYjMwNmZiNDJhMCIsInQiOjE1ODM4OTU2OTUwODF9";
        System.out.println(Base64.decodeBase64(data1));
        System.out.println(Base64.encodeBase64String("BEdQMzAwMTk2MDAwMDIAACQAAABwgF3nZ4AAPAAABaAAAAAFeAAoKGIGAx5fBgIZVAAAHGAGAhpqBgEAAAUUHVsGAR1YBgIYXgYBGF0GARhdBgMXWgYCJGIGAxpmBgIaZwYEAAAFDC1iBgIuYwYDJmIAAAAABRgmXAAAH1oAABheAAAaZAAAHGEEARhlAAAAAAU4AAAFPAAABTwAAAU8AAAFPAAABTwAAAU8AAAFPAAABTwAAAU8AAAFPAAABTwAAAU8AAAFPA".getBytes()));
    }
}
