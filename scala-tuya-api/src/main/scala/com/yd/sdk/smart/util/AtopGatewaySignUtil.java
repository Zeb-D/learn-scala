package com.yd.sdk.smart.util;

import com.yd.sdk.smart.internal.MD5Util;
import com.yd.sdk.smart.model.ApiRequestDO;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * 设备签名工具
 *
 */
public class AtopGatewaySignUtil {

	private static TreeMap<String, String> paramsBuild(ApiRequestDO apiRequestDo) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("a", apiRequestDo.getApi());
		params.put("t", apiRequestDo.getT());
		if(!StringUtils.isEmpty(apiRequestDo.getGwId())){
			params.put("devId", apiRequestDo.getGwId());
		}
		if(!StringUtils.isEmpty(apiRequestDo.getApiContextDo().getUid())){
			params.put("uuid", apiRequestDo.getApiContextDo().getUid());
		}
		params.put("other", apiRequestDo.getOtherData());

		//1.0的都不签名。由于模块的程序，有时有传1.0，有的又没有传1.0
		if (apiRequestDo.getApi().startsWith("tuya.device.")
				|| !(apiRequestDo.getApiContextDo().getApiVersion().equals("1.0"))) {
			params.put("v", apiRequestDo.getApiContextDo().getApiVersion());
		}

		if (!StringUtils.isEmpty(apiRequestDo.getApiContextDo().getUid())){
			params.put("uuid", apiRequestDo.getApiContextDo().getUid());		
		}
		return params;
	}

	private static String signAssembly(TreeMap<String, String> params, String secretKey) {
		//LinkedHashMap 使用LinkedHashMap保持顺序
		StringBuilder str = new StringBuilder();
		Set<String> keySet = params.keySet();
		Iterator<String> iter = keySet.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			if (StringUtils.isBlank(params.get(key))) {
				continue;
			}
			str.append(key);
			str.append("=");
			str.append(params.get(key));
			str.append("||");
		}
		str.append(secretKey);
		return str.toString();
	}
	
	public static String getSign(ApiRequestDO apiRequestDo, String secretKey) {
		TreeMap<String, String> params = paramsBuild(apiRequestDo);
		String signString = signAssembly(params, secretKey);
		return signString;
	}

	public static boolean signValidate(ApiRequestDO apiRequestDo, String secretKey, StringBuilder errorMsg) {
		String signString = getSign(apiRequestDo, secretKey);
		String sign = MD5Util.getMD5(signString.getBytes());
		if (apiRequestDo.getOpenSign() && !sign.equals(apiRequestDo.getSign().trim())) {
			errorMsg.append("签名串:").append(signString).append(",签名值:").append(sign);
			errorMsg.append(",比较签名值=").append(apiRequestDo.getSign()).append("\r\n");
			return false;
		}
		return true;
	}
	
	public static String urlAssembly(ApiRequestDO apiRequestDo){
//		String url = "http://a.gw.tuyacn.com/gw.json";
		String url = "http://a.gw.cn.wgine.com/gw.json";
		//String url = "http://a1.tuyaus.com/gw.json";
		StringBuffer sb = new StringBuffer(url);
		url = sb.append("?")
				.append("a=").append(apiRequestDo.getApi())
				.append("&devId=").append(apiRequestDo.getGwId())
				.append("&other=").append(apiRequestDo.getOtherData())
				.append("&t=").append(apiRequestDo.getT())
				.append("&v=").append(apiRequestDo.getApiContextDo().getApiVersion())
				.append("&data=").append(apiRequestDo.getData())
				.append("&uuid=").append(apiRequestDo.getApiContextDo().getUid())
				.append("&sign=").append(apiRequestDo.getSign()).toString();
		System.err.println(apiRequestDo.getData());
		return url;
	}

	public static String encrypt(String keyStr,String data) throws Exception {
	    SecretKeySpec key = new SecretKeySpec(keyStr.getBytes(), "AES");
	    Cipher c = Cipher.getInstance("AES");
	    c.init(1, key);
	    byte[] encVal = c.doFinal(data.getBytes());
	    String encryptedValue = byte2hex(encVal);
	    return encryptedValue;
	}

	public static String byte2hex(byte[] b) {
	    String hs = "";
	    String stmp = "";

	    for(int n = 0; n < b.length; ++n) {
	        stmp = Integer.toHexString(b[n] & 255);
	        if(stmp.length() == 1) {
	            hs = hs + "0" + stmp;
	        } else {
	            hs = hs + stmp;
	        }
	    }

	    return hs.toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		/*
		String sign = "kh58gyu5ns8r78ukeq9hwtc4gh8uyt5ba=tuya.cloud.device.dp.batch.get|clientId=efckusyftr83t4mo9uy5|lang=zh|os=Linux|postData={\"devIds\":[\"002003825ccf7fff0dab\",\"002003825ccf7fff0dd9\",\"002003825ccf7fff0e1f\"],\"needOffline\":true}|time=1493967952|v=1.0";
		
		String result = MD5Util.getMD5(sign);
		System.err.println(result);
		
		String seckey = "3b6dc26523e0b27c";
		String localKey = "bdc7ab86560f7e52";
		String signed = MD5Util.getMD5(seckey);
		//String signed = MD5Util.getMD5(localKey.getBytes());
		System.out.println(signed);
		
		String password = StringUtils.substring(signed, 8, 24);
		System.out.println(password);
		String str = "2.156f74850bfe8c112Y1JnQlsE7RgIzqEYEloGb+cHCZchfqnHu3OhzEEXChkzLqq9EdmcjxZPHBgK4VA0I2xk+olToQbla64C+aXLWk0W5cw0IFoDTuUw0KLckcnw5FCWdeSe3dMRZ3Wa7Cmb";
		
		String encodeStr = "Y1JnQlsE7RgIzqEYEloGb+cHCZchfqnHu3OhzEEXChkzLqq9EdmcjxZPHBgK4VA0I2xk+olToQbla64C+aXLWk0W5cw0IFoDTuUw0KLckcnw5FCWdeSe3dMRZ3Wa7Cmb";
		*/
//		String seckey = "8bfa3f2a77f6a7a2";
//		//String localKey = "bdc7ab86560f7e52";
//		String signed = MD5Util.getMD5(seckey);
//		//String signed = MD5Util.getMD5(localKey.getBytes());
//		System.out.println(signed);
//		
//		String password = StringUtils.substring(signed, 8, 24);
//		System.out.println(password);
		String sign= "a=tuya.device.register||t=1501818303000||uuid=y91fu66er8u7OI6w5k15||v=4.0||j3iu90Pkjhoiu872vvt3pWX0u82lkkju";
		System.out.println(MD5Util.getMD5(sign));
	}
}