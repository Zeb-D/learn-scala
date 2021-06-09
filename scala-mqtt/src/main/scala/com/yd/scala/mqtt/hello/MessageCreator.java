package com.yd.scala.mqtt.hello;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Hex;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 消息生成器
 * {
 * "protocol": 5,
 * "data": "使用secret AES加密后的数据",
 * "t": 1459168450,//时间戳
 * "pv": "2.0", //协议版本号
 * "token":"12aqeq1",//就像接口一样的token
 * "sign": "63580128ef644ecce747a606d140b8f8"//签名串
 * }
 *
 * @author created by Zeb-D on 2019-08-09 21:08
 */
public class MessageCreator {
    private static final String TOKEN = "c848f836bbd7103102dfcff4449b4dfd";
    private static final String SECRET = "585fa7bd52cc08d66c4581e27d25a701";//"jvvesfswkx9ffsahurm5q7umgxp7e75w";
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

    public static void main(String[] args) throws Exception {
        String str = "aaaa";
        String encodeStr = Hex.encodeHexString(str.getBytes("UTF-8"));
        System.out.println("str:" + str);
        System.out.println("encodeStr:" + encodeStr);
        System.out.println("decodeStr2:" + encodeStr.getBytes("UTF-8"));

        String payload = createPayload();
        System.out.println(payload);
//        String enco = ProtocolAESBase64Util.encrypt(payload, "3146c5967a93759b");
//        String deco = ProtocolAESBase64Util.decrypt(enco, "3146c5967a93759b");
//        System.out.println("encod:" + enco + "\ndeco:" + deco);
//        byte[] json = payload.getBytes("UTF-8");
//        String encode = Hex.encodeHexString(json);
//        byte[] decode = Hex.decodeHex(encode.toCharArray());
//
//        System.out.println("json:" + new String(json, "UTF-8"));
//        System.out.println("encode:" + encode);
//        System.out.println("decode length:" + decode.length);
//        System.out.println("string:" + new String(decode, "UTF-8"));
//
//        String aa = "7b2270223a352c226465764964223a22766465766f313533363239313630353033303736222c2274223a313536353430343038373637342c2264617461223a224e4561576a71492b52555862566d4c307a33372f68474f3076754348475346696563342f57694e75343963653979424a436177615748514978493251432f382f3868666d68776856524e6d30342f336f42617a4749374d5232586749574462434857736b34724f3175664d502b62416f466850716b59344662483439677754422b446332496c6a4549752b66796b54437631645449773d3d222c227076223a22322e30222c227369676e223a2232376363616465646634373262373038222c22746f6b656e223a226235363364393930313737333963663965666363373032363261323936316165227d";
//        System.out.println("aa:" + aa.length());


        String data = "GXcTCVs4440sz8Vjt6w9ZQg9RB7IkJJYsnLH1uK+SA+yDCcaNeKTXHCU1YkgTy7gvVPoe4U7Nbz4wauDYHdMwcTUz8JlD1fb4m4CaA==";
        String p = "585fa7bd52cc08d66c4581e27d25a701";
        String decryptData = ProtocolAESBase64Util.decrypt(data, "68211f0c02817e7e");
        System.out.println(decryptData);
    }

    /**
     * {
     * *                "code":"switch_led",
     * *                "value":true
     * *                },{
     * *                "code":"bright",
     * *                "value":30
     * *                }
     *
     * @return
     */
    public static final String createPayload() throws Exception {
        String bizCode = "command";
        Map<String, Object> bizData = new HashMap<String, Object>();
        bizData.put("basic_indicator", true);
//        bizData.put("bright_value", new Random().nextInt(255));
//        bizData.put("work_mode", getWorkMode());//工作模式
        String jsonStr = createPayload(bizCode, bizData, TOKEN, SECRET.substring(8, 24)).toJSONString();

        return jsonStr;//进行16进制编码
    }

    private static final String getWorkMode() {
        if (new Random().nextBoolean()) {
            return "colour";
        }
        return "white";
    }

    /**
     * @param bizCod  业务码
     * @param bizData 业务数据
     * @param token   当前用户的token
     * @param secret  开发者的Secret
     * @return 返回已经加密data的payload
     */
    public static JSONObject createPayload(String bizCod, Map<String, Object> bizData, String token,
                                           String secret) throws Exception {
        JSONObject object = new JSONObject();
        object.put(PAYLOAd_PROTOCOL, 5);
        object.put(PAYLOAd_PV, "2.0");
        long time = System.currentTimeMillis();//13位的时间戳
        object.put(PAYLOAd_T, time);
        object.put(PAYLOAd_TOKEN, token);
        JSONObject data = createData(bizCod, createBizData(bizData));
        String aesData = ProtocolAESBase64Util.encrypt(data.toString(), secret);
//        System.out.println(data.toJSONString() + "====>\n" + aesData);
        System.out.println(ProtocolAESBase64Util.decrypt(aesData, secret));
        object.put(PAYLOAd_DATA, aesData);
        String signed = ProtocolSignUtil.getSign(object.getInteger(PAYLOAd_PROTOCOL), time,
                object.getString(PAYLOAd_PV), aesData, secret).substring(8, 24);

        System.out.println("signed:" + signed);
        System.out.println(System.currentTimeMillis() + " json:" + object.toJSONString());
        object.put(PAYLOAd_SIGN, signed);

        return object;
    }

    /**
     * @param bizCode
     * @param bizData
     * @return 生成业务消息体
     */
    public static JSONObject createData(String bizCode, JSONArray bizData) {
        JSONObject data = new JSONObject();
        data.put(DATA_BIZ_CODE, bizCode);
        data.put(DATA_BIZ_DATA, bizData);
        return data;
    }

    /**
     * @param bizData {
     *                "code":"switch_led",
     *                "value":true
     *                },{
     *                "code":"bright",
     *                "value":30
     *                }
     * @return 返回bizCode=command的控制指令
     */
    public static JSONArray createBizData(Map<String, Object> bizData) {
        if (bizData == null || bizData.isEmpty()) {
            return null;
        }
        JSONArray bizDataArray = new JSONArray();
        for (Map.Entry<String, Object> entry : bizData.entrySet()) {
            JSONObject object = new JSONObject();
            object.put(DATA_BIZ_DATA_CODE, entry.getKey());
            object.put(DATA_BIZ_DATA_VALUE, entry.getValue());
            bizDataArray.add(object);
        }
        return bizDataArray;
    }
}
