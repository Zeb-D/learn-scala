
package com.yd.sdk.smart.sign;

import com.yd.sdk.smart.internal.MD5Util;
import com.yd.sdk.smart.model.ApiRequestDO;

import java.util.TreeMap;

public class Md5Sign implements SignAlgorithm {

	public String getSignString(ApiRequestDO apiRequestDo) {
		TreeMap<String, String> params = paramsBuild(apiRequestDo);
		String sign = singAssembly(params);
		return sign;
	}


	public String getSignInput(ApiRequestDO apiRequestDo) {
		TreeMap<String, String> params = paramsBuild(apiRequestDo);
		return SignUtils.signParamsAssembly(params);
	}

	/**
	 * @param params
	 * @return
	 */
	protected String singAssembly(TreeMap<String, String> params) {
		//LinkedHashMap 使用LinkedHashMap保持顺序
		String strValue = SignUtils.signParamsAssembly(params);
		String sign = MD5Util.getMD5(strValue.getBytes());
		return sign;
	}

	protected TreeMap<String, String> paramsBuild(ApiRequestDO apiRequestDo) {
		TreeMap<String, String> params = new TreeMap<String, String>();
		params.put("a", apiRequestDo.getApi());
		params.put("postData", apiRequestDo.getData() != null ? apiRequestDo.getData() : "");
		params.put("time", apiRequestDo.getT());
		params.put("v", apiRequestDo.getApiContextDo().getApiVersion());
		params.put("lat", apiRequestDo.getApiContextDo().getLat());
		params.put("lon", apiRequestDo.getApiContextDo().getLon());
		params.put("lang", apiRequestDo.getApiContextDo().getLang());
		params.put("sid", apiRequestDo.getSession());
		params.put("deviceId", apiRequestDo.getApiContextDo().getDeviceid());
		params.put("imei", apiRequestDo.getApiContextDo().getImei());
		params.put("imsi", apiRequestDo.getApiContextDo().getImsi());
		params.put("appVersion", apiRequestDo.getApiContextDo().getAppVersion());
		params.put("ttid", apiRequestDo.getApiContextDo().getTtid());
		params.put("isH5", apiRequestDo.getApiContextDo().getIsH5());
		params.put("h5Token", apiRequestDo.getH5Token());
		params.put("os", apiRequestDo.getApiContextDo().getOs());
		params.put("clientId", apiRequestDo.getAppInfoDo().getClientId());
		params.put("appSecret", apiRequestDo.getAppInfoDo().getHashKey());
		return params;
	}

}
