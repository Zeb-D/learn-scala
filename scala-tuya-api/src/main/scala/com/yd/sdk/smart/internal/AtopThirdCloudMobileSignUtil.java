package com.yd.sdk.smart.internal;

import com.yd.sdk.smart.model.RequestMessage;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class AtopThirdCloudMobileSignUtil {

	private static TreeMap<String, String> paramsBuild(RequestMessage request) {
		TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("a", request.getApi());
        params.put("v", request.getApiVersion());
        params.put("lang", request.getLang());
        params.put("deviceId", request.getDeviceid());
        params.put("os", request.getOs());
        params.put("clientId", request.getClientId());
		params.put("time", String.valueOf(request.getTime()));

		if (StringUtils.isNotBlank(request.getSession())) {
			params.put("sid", request.getSession());
		}
		if (StringUtils.isNotBlank(request.getPostData())) {
			params.put("postData", request.getPostData());
		}
		return params;
	}

	private static String signAssembly(TreeMap<String, String> params, String secretKey) {
		//LinkedHashMap 使用LinkedHashMap保持顺序
		StringBuilder str = new StringBuilder();
        str.append(secretKey);
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
			str.append("|");
		}
        String strValue = str.toString();
        strValue = strValue.substring(0, (strValue.length() - 1));
        return strValue;
	}

	public static String getSign(RequestMessage request, String secretKey) {
		TreeMap<String, String> params = paramsBuild(request);
		String signString = signAssembly(params, secretKey);
		System.out.println("assembly="+signString);
		String sign = MD5Util.getMD5(signString.getBytes());
		return sign;
	}



}
