package com.yd.http.testing;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.AbstractHttpMessage;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author created by Zeb灬D on 2019-10-28 10:08
 */
public class HttpClient {
    public final static String contentType = "application/json";
    protected static CloseableHttpClient httpClient = HttpClientFactory.getInstance().getDefaultClient();

    //先支持queryMap、bodyMap参数随机性
    public static void Request(String method, String pattern, Map<String, String> urlMap,
                               Map<String, String> queryMap, Map<String, Object> bodyMap) {
        int querySize = MapUtils.isEmpty(queryMap) ? 1 : queryMap.size();
        int bodySize = MapUtils.isEmpty(bodyMap) ? 1 : bodyMap.size();
        if (MapUtils.isNotEmpty(queryMap)) {
            for (int j = 0; j < querySize; j++) {
                String queryKey = queryMap.keySet().toArray()[j].toString();
                Map<String, String> queryTemp = new HashMap<String, String>(queryMap);
                queryTemp.remove(queryKey);
                if (MapUtils.isNotEmpty(bodyMap)) {
                    for (int i = 0; i < bodySize; i++) {
                        String bodyKey = bodyMap.keySet().toArray()[i].toString();
                        Map<String, Object> bodyTemp = new HashMap<String, Object>(bodyMap);
                        bodyTemp.remove(bodyKey);
//                        System.out.println("2222---->");
                        Response pre = doRequest(HttpUtil.HOST, method, pattern, urlMap, queryTemp, bodyTemp, Response.class);
                        Response pro = doRequest(HttpUtil.ProHOST, method, pattern, urlMap, queryTemp, bodyTemp, Response.class);
                        if (pre.success != pro.success || !pre.success) {
                            System.out.println(method + "-->pattern:" + pattern + "-->queryTemp:" + queryTemp + "-->bodyTemp:" + bodyTemp);
                            System.err.println(pre + "-->:\npro.result:" + pro);
                        } else if (StringUtils.equals(HttpUtil.getMD5(pre.result), HttpUtil.getMD5(pro.result))) {
                            System.out.println(method + "2-->pattern:" + pattern + "-->queryTemp:" + queryTemp + "-->bodyTemp:" + bodyTemp);
                            System.err.println(pre + "2-->:\npro.result:" + pro);
                        }
                        continue;
                    }
                } else {
//                    System.out.println("1111---->");
                    Response pre = doRequest(HttpUtil.HOST, method, pattern, urlMap, queryTemp, bodyMap, Response.class);
                    Response pro = doRequest(HttpUtil.ProHOST, method, pattern, urlMap, queryTemp, bodyMap, Response.class);
                    if (pre.success != pro.success  || !pre.success) {
                        System.out.println(method + "-->pattern:" + pattern + "-->queryTemp:" + queryTemp);
                        System.err.println(pre + "-->:\npro.result:" + pro);
                    } else if (StringUtils.equals(HttpUtil.getMD5(pre.result), HttpUtil.getMD5(pro.result))) {
                        System.out.println(method + "2-->pattern:" + pattern + "-->queryTemp:" + queryTemp);
                        System.err.println(pre + "2-->:\npro.result:" + pro);
                    }
                    continue;
                }
            }
            return;
        }

        if (MapUtils.isNotEmpty(bodyMap)) {
            for (int i = 0; i < bodySize; i++) {
                String bodyKey = queryMap.keySet().toArray()[i].toString();
                Map<String, Object> bodyTemp = new HashMap<>(bodyMap);
                bodyTemp.remove(bodyKey);
//                System.out.println("3333---->");
                Response pre = doRequest(HttpUtil.HOST, method, pattern, urlMap, queryMap, bodyTemp, Response.class);
                Response pro = doRequest(HttpUtil.ProHOST, method, pattern, urlMap, queryMap, bodyTemp, Response.class);
                if (pre.success != pro.success  || !pre.success) {
                    System.out.println(method + "-->pattern:" + pattern + "-->bodyMap:" + bodyMap);
                    System.err.println(pre + "-->:\npro.result:" + pro);
                } else if (StringUtils.equals(HttpUtil.getMD5(pre.result), HttpUtil.getMD5(pro.result))) {
                    System.out.println(method + "2-->pattern:" + pattern + "-->bodyMap:" + bodyMap);
                    System.err.println(pre + "2-->:\npro.result:" + pro);
                }
                continue;
            }
            return;
        }

//        System.out.println("4444---->");
        Response pre = doRequest(HttpUtil.HOST, method, pattern, urlMap, queryMap, bodyMap, Response.class);
        Response pro = doRequest(HttpUtil.ProHOST, method, pattern, urlMap, queryMap, bodyMap, Response.class);
        if (pre.success != pro.success  || !pre.success) {
            System.out.println(method + "-->pattern:" + pattern + "-->queryMap:" + queryMap + "-->bodyMap:" + bodyMap);
            System.err.println(pre + "-->:\npro.result:" + pro);
        } else if (!StringUtils.equals(HttpUtil.getMD5(pre.result), HttpUtil.getMD5(pro.result))) {
            System.out.println(method + "2-->pattern:" + pattern + "-->queryMap:" + queryMap + "-->bodyMap:" + bodyMap);
            System.err.println(pre + "2-->:\npro.result:" + pro);
        }
    }

    public static void main(String[] args) {
        HttpUtil.Init("https://openapi-cn.wgine.com", "u54a9nj4vw347hypgem7", "jvvesfswkx9ffsahurm5q7umgx123456");
        HttpUtil.InitToken("a56aed1375573b9280535de764419ad3");
        HttpUtil.InitProHOST("https://openapi.tuyacn.com");
        String pattern = "/v1.0/apps/{schema}/users";
        Map<String, String> urlMap = new HashMap<>();
        urlMap.put("schema", "ydsmart");
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("page_no", "1");
        queryMap.put("page_size", "10");
        Map<String, Object> bodyMap = null;
        Response response = doRequest("https://openapi-cn.wgine.com", "GET", pattern, urlMap, queryMap, bodyMap, Response.class);
        System.out.println(response);

        Request("GET", pattern, urlMap, queryMap, bodyMap);


        System.out.println(getHeader());
    }

    //所有的请求最终执行
    public static final <T> T doRequest(String host, String method, String pattern, Map<String, String> urlMap,
                                        Map<String, String> queryMap, Map<String, Object> bodyMap,
                                        Class<T> RespClazz) {
        String url = host + pattern;
        if (MapUtils.isNotEmpty(urlMap)) {//组装url，不包括query参数
            url = buildUrl(url, urlMap);
        }
        if (method != "POST" && method != "DELETE" && method != "PUT" && method != "GET") {
            throw new RuntimeException("只能支持POST、DELETE、PUT、GET");
        }
        //增加query参数
        List<NameValuePair> queryParams = paramsConverter(queryMap);
        HttpEntityEnclosingRequestBase requestBase = getRequestBase(method, buildQuery(url, queryParams));

        //增加请求头
        addRequestHeaders(requestBase);

        //增加body
        addRequestBody(requestBase, bodyMap);

        String result = "";
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(requestBase);
            validHttpStatus(httpResponse);
            result = getResultContent(httpResponse);
            httpResponse.close();
        } catch (IOException e1) {
            requestBase.abort();
            throw new RuntimeException(e1);
        } finally {
            requestBase.releaseConnection();
        }

        return JSONObject.parseObject(result, RespClazz);
    }

    public static HttpEntityEnclosingRequestBase getRequestBase(String method, URI uri) {
        switch (method) {
            case "POST":
                return new HttpPost(uri);
            case "PUT":
                return new HttpPut(uri);
            case "DELETE":
                new RequestBase("DELETE", uri);
            case "GET":
                new RequestBase("GET", uri);
        }

        return new RequestBase("GET", uri);
    }

    protected static String getResultContent(HttpResponse httpResponse) throws ParseException, IOException {
        HttpEntity entity = httpResponse.getEntity();
        String resultString = null;
        if (entity != null) {
            resultString = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            EntityUtils.consume(entity);
        }

        return resultString;
    }

    private static void validHttpStatus(CloseableHttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            System.err.println(statusCode);
        }
    }

    //增加请求body
    public static final void addRequestBody(HttpEntityEnclosingRequestBase requestBase, Map<String, Object> bodyMap) {
        String postData = JSONObject.toJSONString(bodyMap);
        StringEntity strinEntity = new StringEntity(postData, StandardCharsets.UTF_8);
        requestBase.setEntity(strinEntity);
    }

    //增加请求头
    public static final void addRequestHeaders(AbstractHttpMessage httpMessage) {
        List<Header> headers = getHeader();
        for (Header header : headers) {
            httpMessage.addHeader(header);
        }
    }

    public static List<Header> getHeader() {
        String clientId = HttpUtil.ClientId;
        String sercet = HttpUtil.Secret;
        String accessToken = HttpUtil.AccessToken;
        //header参数
        List<Header> headers = new ArrayList<Header>();
        Header contentHeader = new BasicHeader("Content-Type", contentType);
        Header clientHeader = new BasicHeader("client_id", clientId);
        Header signMethodHeader = new BasicHeader("sign_method", "HMAC-SHA256");
        Long time = System.currentTimeMillis();
        Header timeHeader = new BasicHeader("t", time.toString());
        Header accessHeader = new BasicHeader("access_token", accessToken);
        Header signHeader = new BasicHeader("sign", HttpUtil.HMAC(clientId + accessToken + time.toString(), sercet).toUpperCase());
        headers.add(accessHeader);
        headers.add(signHeader);
        headers.add(clientHeader);
        headers.add(contentHeader);
        headers.add(timeHeader);
        headers.add(signMethodHeader);
        return headers;
    }

    //将Pattern 组装
    public static final String buildUrl(String urlPattern, Map<String, String> urlMap) {
        if (StringUtils.isEmpty(urlPattern)) {
            return urlPattern;
        }
        int beginIndex = urlPattern.indexOf("{", 0);
        int endIndex = urlPattern.indexOf("}", 0);
        if (beginIndex > -1 && endIndex > beginIndex) {
            String uriKey = urlPattern.substring(beginIndex + 1, endIndex);
            String temp = urlPattern.substring(0, beginIndex) + getMap(uriKey, urlMap) + urlPattern.substring(endIndex + 1);
            return buildUrl(temp, urlMap);
        }

        return urlPattern;
    }

    public static <T, U> U getMap(T key, Map<T, U> urlMap) {
        return urlMap.get(key);
    }

    protected static URI buildQuery(String url, List<NameValuePair> params) {
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null && params.size() > 0) {
                builder.addParameters(params);
            }

            return builder.build();
        } catch (URISyntaxException var4) {
            var4.printStackTrace();
            return null;
        }
    }


    public static List<NameValuePair> paramsConverter(Map<String, String> params) {
        if (MapUtils.isEmpty(params)) {
            return Collections.emptyList();
        }
        List<NameValuePair> nvps = new LinkedList();
        Set<Map.Entry<String, String>> paramsSet = params.entrySet();
        Iterator<Map.Entry<String, String>> var3 = paramsSet.iterator();

        while (var3.hasNext()) {
            Map.Entry<String, String> paramEntry = var3.next();
            nvps.add(new BasicNameValuePair(paramEntry.getKey(), paramEntry.getValue()));
        }

        return nvps;
    }
}

class RequestBase extends HttpEntityEnclosingRequestBase {
    String method;

    public RequestBase(String method, URI uri) {
        super.setURI(uri);
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method;
    }
}

class Response {
    boolean success;
    Long t;
    String result;
    Integer code;
    String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Long getT() {
        return t;
    }

    public void setT(Long t) {
        this.t = t;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", t=" + t +
                ", result='" + result + '\'' +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}