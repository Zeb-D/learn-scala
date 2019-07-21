
package com.yd.sdk.smart.sign;

import com.yd.sdk.smart.model.ApiRequestDO;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class SignUtils {

	public static String signParamsAssembly(TreeMap<String, String> params) {
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
		String strValue = str.toString();
		strValue = strValue.substring(0, (strValue.length() - 2));
		return strValue;
	}

	public static TreeMap<String, String> signParamsBuild(ApiRequestDO apiRequestDo) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("a", apiRequestDo.getApi());
		params.put("v", apiRequestDo.getApiContextDo().getApiVersion());
		params.put("lat", apiRequestDo.getApiContextDo().getLat());
		params.put("lon", apiRequestDo.getApiContextDo().getLon());
		params.put("lang", apiRequestDo.getApiContextDo().getLang());
		params.put("deviceId", apiRequestDo.getApiContextDo().getDeviceid());
		params.put("imei", apiRequestDo.getApiContextDo().getImei());
		params.put("imsi", apiRequestDo.getApiContextDo().getImsi());
		params.put("appVersion", apiRequestDo.getApiContextDo().getAppVersion());
		params.put("ttid", apiRequestDo.getApiContextDo().getTtid());
		params.put("isH5", apiRequestDo.getApiContextDo().getIsH5());
		params.put("h5Token", apiRequestDo.getH5Token());
		params.put("os", apiRequestDo.getApiContextDo().getOs());
//		params.put("clientId", apiRequestDo.getAppInfoDo().getId().toString());

		if (StringUtils.isNotBlank(apiRequestDo.getN4h5())) {
			params.put("n4h5", apiRequestDo.getN4h5());
		}
		params.put("sp", apiRequestDo.getSp());
		return params;
	}
}
