
package com.yd.sdk.smart.sign;


import com.yd.sdk.smart.internal.MD5Util;
import com.yd.sdk.smart.internal.Md5HexUtil;
import com.yd.sdk.smart.model.ApiRequestDO;
import com.yd.sdk.smart.util.RandomUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class PcSign implements SignAlgorithm {


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
			str.append("-");
			str.append(params.get(key));
			str.append("|");
		}
		String strValue = str.toString();
		strValue = strValue.substring(0, (strValue.length() - 1));
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
		params.put("lang", apiRequestDo.getApiContextDo().getLang());
		params.put("clientId", apiRequestDo.getAppInfoDo().getClientId());
		params.put("os", apiRequestDo.getApiContextDo().getOs());
		params.put("deviceId", apiRequestDo.getApiContextDo().getDeviceid());

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

		signStr.append(apiRequestDo.getT());

		signStr.append(apiRequestDo.getAppInfoDo().getHashKey());
		signStr.append(baseSign);
		if (StringUtils.isNotBlank(apiRequestDo.getSession())) {
			signStr.append(apiRequestDo.getSession());
		}
		if (StringUtils.isNotBlank(apiRequestDo.getData())) {
			signStr.append(Md5HexUtil.md5Hex(apiRequestDo.getData()));
		}

		if (StringUtils.isNotBlank(apiRequestDo.getSession())) {
			signStr.append(RandomUtil.getRandomByMod(apiRequestDo.getSession(), 16));
		}
		return signStr.toString();
	}

	public String getSignInput(ApiRequestDO apiRequestDo) {
		return createSignInput(apiRequestDo);
	}

	public static void main(String[] args) {
		//		ApiRequestDO a = new ApiRequestDO();
		//		AppInfoDO i = new AppInfoDO();
		//		i.setId(44);
		//		i.setHashKey("e7cf7fb272f111e599b074d435ccce42");
		//		a.setAppInfoDo(i);
		//		a.setT("1444982612");
		//		a.setApi("s.open.product.get");
		//
		//		ApiContextDO apiContextDo = new ApiContextDO();
		//		apiContextDo.setLang("zh");
		//		apiContextDo.setApiVersion("1.0");
		//		apiContextDo.setSid("c0014326N11438210NCOSFD3881bb995a20257f1629ab753cc9686df");
		//		a.setApiContextDo(apiContextDo);
		//		a.setData("{\"id\":\"14\"}");
		//		a.setSession("c0014326N11438210NCOSFD3881bb995a20257f1629ab753cc9686df");
		//
		//		PcSign pc = new PcSign();
		//		System.out.println(pc.getSignString(a));
		//
		//		System.out.println(RandomUtil.getRandomByMod("c0014326N11438210NCOSFD3881bb995a20257f1629ab753cc9686df", 16));
		//        String s = Md5HexUtil.md5Hex("{\"id\":1}");
		//        System.out.println(s);

	}
}
