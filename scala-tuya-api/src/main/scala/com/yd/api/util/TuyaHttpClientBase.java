package com.yd.api.util;

import com.yd.api.config.ClientConfig;
import com.yd.api.internal.TuyaHttpClientFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

public class TuyaHttpClientBase {
    protected static final Log logger = LogFactory.getLog(TuyaHttpClientBase.class);
    protected static volatile TuyaHttpClient instance;
    protected static RequestConfig requestConfig;
    protected volatile CloseableHttpClient httpClient;
    protected static int timeout = 20000;
    protected static int maxConnects = 200;
    protected static int tryMax = 3;

    public static void setInstance(TuyaHttpClient instance) {
        instance = instance;
    }

    TuyaHttpClientBase (ClientConfig clientConfig){
        this.init(clientConfig);
    }

    TuyaHttpClientBase (){
        this.init();
    }

    protected void init() {
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout).setConnectTimeout(6000).setSocketTimeout(timeout).build();
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setConnectionTimeout(timeout);
        clientConfig.setMaxConnections(maxConnects);
        clientConfig.setMaxErrorRetry(tryMax);
        this.httpClient = (CloseableHttpClient) TuyaHttpClientFactory.getInstance().getDefaultClient(clientConfig);
    }

    protected void init(ClientConfig clientConfig) {
        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeout).setConnectTimeout(6000).setSocketTimeout(timeout).build();
        this.httpClient = (CloseableHttpClient) TuyaHttpClientFactory.getInstance().getDefaultClient(clientConfig);
    }

    protected String getResultContent(HttpResponse httpResponse, Charset CHARSET) throws ParseException, IOException {
        if (CHARSET == null) {
            CHARSET = Consts.UTF_8;
        }

        HttpEntity entity = httpResponse.getEntity();
        String resultString = null;
        if (entity != null) {
            resultString = EntityUtils.toString(entity, CHARSET);
            EntityUtils.consume(entity);
        }

        return resultString;
    }

    protected HttpGet initHttpGetRequest(HttpGet requestBase, RequestConfig config, List<Header> headers) {
        if (config != null) {
            requestBase.setConfig(config);
        } else {
            requestBase.setConfig(requestConfig);
        }

        if (headers != null && !headers.isEmpty()) {
            Iterator var4 = headers.iterator();

            while(var4.hasNext()) {
                Header header = (Header)var4.next();
                requestBase.addHeader(header);
            }
        }

        return requestBase;
    }

    protected HttpPost initHttpPostRequest(HttpPost requestBase, RequestConfig config, List<Header> headers) {
        if (config != null) {
            requestBase.setConfig(config);
        } else {
            requestBase.setConfig(requestConfig);
        }

        if (headers != null && !headers.isEmpty()) {
            Iterator var4 = headers.iterator();

            while(var4.hasNext()) {
                Header header = (Header)var4.next();
                requestBase.addHeader(header);
            }
        }

        return requestBase;
    }

    protected HttpPut initHttpPutRequest(HttpPut requestBase, RequestConfig config, List<Header> headers) {
        if (config != null) {
            requestBase.setConfig(config);
        } else {
            requestBase.setConfig(requestConfig);
        }

        if (headers != null && !headers.isEmpty()) {
            Iterator var4 = headers.iterator();

            while(var4.hasNext()) {
                Header header = (Header)var4.next();
                requestBase.addHeader(header);
            }
        }

        return requestBase;
    }

    /**
     * 通用转换
     * @param requestBase
     * @param config
     * @param headers
     * @return
     */
//    protected HttpEntityEnclosingRequestBase initHttpPutRequest(HttpEntityEnclosingRequestBase requestBase, RequestConfig config, List<Header> headers) {
//        if (config != null) {
//            requestBase.setConfig(config);
//        } else {
//            requestBase.setConfig(requestConfig);
//        }
//
//        if (headers != null && !headers.isEmpty()) {
//            Iterator var4 = headers.iterator();
//
//            while(var4.hasNext()) {
//                Header header = (Header)var4.next();
//                requestBase.addHeader(header);
//            }
//        }
//
//        return requestBase;
//    }


    protected HttpDelete initHttpDeleteRequest(HttpDelete requestBase, RequestConfig config, List<Header> headers) {
        if (config != null) {
            requestBase.setConfig(config);
        } else {
            requestBase.setConfig(requestConfig);
        }

        if (headers != null && !headers.isEmpty()) {
            Iterator var4 = headers.iterator();

            while(var4.hasNext()) {
                Header header = (Header)var4.next();
                requestBase.addHeader(header);
            }
        }

        return requestBase;
    }



    protected URI buildUri(String url, List<NameValuePair> params) {
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null && params.size() > 0) {
                builder.addParameters(params);
            }

            return builder.build();
        } catch (URISyntaxException var4) {
            logger.error("", var4);
            return null;
        }
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }
}
