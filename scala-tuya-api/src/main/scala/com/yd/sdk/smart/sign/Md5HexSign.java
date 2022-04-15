
package com.yd.sdk.smart.sign;

import com.yd.sdk.smart.internal.AtopThirdMobileSignUtil;
import com.yd.sdk.smart.internal.MD5Util;
import com.yd.sdk.smart.internal.Md5HexUtil;
import com.yd.sdk.smart.model.ApiRequestDO;
import com.yd.sdk.smart.model.RequestMessage;
import com.yd.sdk.smart.util.RandomUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class Md5HexSign implements SignAlgorithm {

	/**
	 * 对基础数据进行组装
	 * 
	 * @param params
	 * @return
	 */
	public static String bodyAssembly(TreeMap<String, String> params) {
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

	/**
	 * 常规签名内容数据组装
	 *
	 * @param apiRequestDo
	 * @return
	 */
	public static TreeMap<String, String> paramsBuild(ApiRequestDO apiRequestDo) {
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
		params.put("clientId", apiRequestDo.getAppInfoDo().getClientId());

		if (StringUtils.isNotBlank(apiRequestDo.getN4h5())) {
			params.put("n4h5", apiRequestDo.getN4h5());
		}
        params.put("sp", apiRequestDo.getSp());
		return params;
	}

	public String getSignString(ApiRequestDO apiRequestDo) {
		String signStr = createSignInput(apiRequestDo);
		String signString = MD5Util.getMD5(signStr);
		return signString;
	}

	private String createSignInput(ApiRequestDO apiRequestDo) {
		TreeMap<String, String> params = paramsBuild(apiRequestDo);
		String baseSign = bodyAssembly(params);
		StringBuilder signStr = new StringBuilder();

		int randomEcodeLen = 16;
		if (apiRequestDo.isAirtakePlant()) {
			randomEcodeLen = 8;
		}

		if (StringUtils.isNotBlank(apiRequestDo.getSession())) {
			signStr.append(RandomUtil.getRandomByMod(apiRequestDo.getSession(), randomEcodeLen));
		}

		signStr.append(apiRequestDo.getAppInfoDo().getHashKey());
		signStr.append(baseSign);
		if (StringUtils.isNotBlank(apiRequestDo.getSession())) {
			signStr.append(apiRequestDo.getSession());
		}
		if (StringUtils.isNotBlank(apiRequestDo.getData())) {
			signStr.append(Md5HexUtil.md5Hex(apiRequestDo.getData()));
		}
		signStr.append(apiRequestDo.getT());
		return signStr.toString();
	}


	public String getSignInput(ApiRequestDO apiRequestDo) {
		return createSignInput(apiRequestDo);
	}

	/**
	 * 老用户如果一直使用,是不会有ecode的,所以如果正常签名失败, 加一次特殊验证, 即不加ecode的签名验证
	 */
	public String getValidateFallbackString(ApiRequestDO apiRequestDo) {
		TreeMap<String, String> params = paramsBuild(apiRequestDo);
		String baseSign = bodyAssembly(params);
		StringBuilder signStr = new StringBuilder();

		signStr.append(apiRequestDo.getAppInfoDo().getHashKey());
		signStr.append(baseSign);
		if (StringUtils.isNotBlank(apiRequestDo.getSession())) {
			signStr.append(apiRequestDo.getSession());
		}
		if (StringUtils.isNotBlank(apiRequestDo.getData())) {
			signStr.append(Md5HexUtil.md5Hex(apiRequestDo.getData()));
		}
		signStr.append(apiRequestDo.getT());
		String signString = MD5Util.getMD5(signStr.toString());
		return signString;
	}

	public String getSign(RequestMessage request, String secretKey) {
		TreeMap<String, String> params = AtopThirdMobileSignUtil.paramsBuild(request);
		String baseSign = bodyAssembly(params);
		StringBuilder signStr = new StringBuilder();

		signStr.append(secretKey);
		signStr.append(baseSign);
		if (StringUtils.isNotBlank(request.getSession())) {
			signStr.append(request.getSession());
		}
		if (StringUtils.isNotBlank(request.getPostData())) {
			signStr.append(Md5HexUtil.md5Hex(request.getPostData()));
		}
		signStr.append(request.getTime());
		String signString = MD5Util.getMD5(signStr.toString());
		return signString;
	}

}
