package com.yd.common.util;

import com.alibaba.fastjson.JSON;
import com.yd.httpClient.Client;
import com.yd.httpClient.ClientRequest;
import com.yd.httpClient.ClientResult;
import com.yd.httpClient.HttpStatus;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author Yd on  2018-02-08
 * @description http-cliet 请求工具类
 **/
public class HttpClientUtil {


    /**
     * http-Get请求
     *
     * @param url  -请求Get接口
     * @param args - 请求参数
     * @return
     */
    public static ClientResult get(String url, Map<String, String> args) {
        Client client = new Client(url + param(args));
        ClientRequest request = new ClientRequest("");
        return client.build(request).get();
    }

    public static ClientResult get(String url) {
        Client client = new Client(url);
        ClientRequest request = new ClientRequest("");
        return client.build(request).get();
    }

    public static ClientResult post(String url,Map<String, String> body) {
        Client client = new Client(url);
        ClientRequest request = new ClientRequest("");
        request.setParams(body);
        return client.build(request).post();
    }

    public static ClientResult post(String url,Map<String, String> body,Map<String, String> header) {
        Client client = new Client(url);
        ClientRequest request = new ClientRequest("");
        request.setHeaders(header);
        request.setParams(body);
        return client.build(request).post();
    }

    public static ClientResult post(String url) {
        Client client = new Client(url);
        ClientRequest request = new ClientRequest("");
        return client.build(request).post();
    }


    public static String param(Map<String, String> args) {
        if (args == null || args.size() == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : args.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
            sb.append("&");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String GET_ORGLIST_URL = "http://b2b.test.pagoda.com.cn/base/pgsearch.php?data=acc_dc&format=json";
        ClientResult clientResult = get(GET_ORGLIST_URL, null);
        if (clientResult != null) {
            HttpStatus status = clientResult.getStatus();
            if (HttpStatus.OK.equals(status)) {
                System.out.println("orgMapList : " + JSON.toJSON(clientResult.getResult()));
            }
        }


    }
}
