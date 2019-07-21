package com.yd.sdk.smart.sign;

import com.yd.sdk.smart.internal.MD5Util;
import org.apache.commons.lang.StringUtils;

import java.util.TreeMap;

public class DataMd5Sign extends Md5Sign {

	protected String singAssembly(TreeMap<String, String> params) {
        String sign = super.singAssembly(params);
        if (params.containsKey("postData") && StringUtils.isNotBlank(params.get("postData"))) {   
            sign += MD5Util.getMD5(params.get("postData"));
            sign = MD5Util.getMD5(sign);                                                       
        }
        return sign;
	}
}
