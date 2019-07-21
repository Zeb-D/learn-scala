package com.yd.sdk.smart.internal;

import com.alibaba.fastjson.JSONObject;
import com.yd.sdk.smart.model.RequestMessage;
import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;


public class AtopThirdMobileSignUtil {

    public static TreeMap<String, String> paramsBuild(RequestMessage request) {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("a", request.getApi());
        params.put("v", request.getApiVersion());
        params.put("lat", request.getLat());
        params.put("lon", request.getLon());
        params.put("lang", request.getLang());
        params.put("deviceId", request.getDeviceid());
        params.put("os", request.getOs());
        params.put("clientId", request.getClientId());
        params.put("time", String.valueOf(request.getTime()));
        if (StringUtils.isNotBlank(request.getSession())) {
            params.put("sid", request.getSession());
        }
        if (StringUtils.isNotBlank(request.getPostData())) {
            params.put("postData", Md5HexUtil.md5Hex(request.getPostData()));
        }
        System.out.println("params="+ JSONObject.toJSONString(params));
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
        System.out.println("signAssembly="+str.toString());
        return str.toString();
    }

    public static String getSign(RequestMessage request, String secretKey) {
        TreeMap<String, String> params = paramsBuild(request);
        String signString = signAssembly(params, secretKey);
        String sign = MD5Util.getMD5(signString.getBytes());
        return sign;
    }
}