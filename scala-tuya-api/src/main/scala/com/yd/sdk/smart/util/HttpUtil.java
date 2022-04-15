package com.yd.sdk.smart.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	
	protected static final int DEFAULT_CONNECTION_TIME_OUT = 2000;
	public static final String system_error_result = "-99";

	
	@SuppressWarnings("deprecation")
	public static String excute(HttpMethod method) {

		HttpClient client = new HttpClient();

		method.setRequestHeader("Connection", "close");

		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");

		// method.setRequestHeader("Time", "yyyyMMddHHmmss");
		// method.setRequestHeader("Encrypt", "MD5(username+pass+time)");
		try {
			client.setTimeout(DEFAULT_CONNECTION_TIME_OUT);
			client.executeMethod(method);

			return method.getResponseBodyAsString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			method.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);

		}
		return "";
	}

	/**
	 * 使用 POST 方式提交数据
	 *
	 * @return
	 */
	private static HttpMethod getPostMethod(String url,
			List<NameValuePair> pairs) {
		PostMethod post = new PostMethod(url);
		NameValuePair[] array = new NameValuePair[pairs.size()];
		post.setRequestBody(pairs.toArray(array));

		return post;
	}

	private static HttpMethod getPostMethod(String url, String params) {
		PostMethod post = new PostMethod(url);
		post.setRequestBody(params);

		System.out.println("params="+params);
		return post;
	}

	/**
	 * 使用 POST 方式提交数据
	 *
	 * @return
	 */
	private static HttpMethod getGetMethod(String url) {

		return new GetMethod(url);
	}

	public static String post(String url, List<NameValuePair> pairs) {
		return excute(getPostMethod(url, pairs));
	}

	public static String post(String url, Map<String,String> params) {
		if (params == null || params.size() == 0 )
			return "";

		JSONObject json = new JSONObject();
		for (String key : params.keySet()) {
			json.put(key, params.get(key));
		}
		String data= json.toJSONString();

		return excute(getPostMethod(url, data));
	}

	public static String post(String url, String params) {
		return excute(getPostMethod(url, params));
	}

	public static String get(String url) {

		return excute(getGetMethod(url));
	}

	/**
	 * 
	 * 
	 * @param src
	 *            String
	 * @return String
	 */
	public static String urlEncode(String src) {
		try {
			src = URLEncoder.encode(src, "UTF-8");

			return src;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return src;
	}

	public String urlDecode(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	public static String formatHttpHeadUrl(String url) {
		if (!url.startsWith("http://"))
			url = "http://" + url;
		if (!url.endsWith("?"))
			url = url + "?";
		return url;
	}


}
