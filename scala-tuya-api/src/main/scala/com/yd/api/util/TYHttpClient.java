package com.yd.api.util;

import com.yd.api.config.ClientConfig;
import com.yd.api.exception.TuyaClientException;
import com.yd.api.internal.TuyaHttpClientHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TYHttpClient extends TYHttpClientBase {

    protected static final Log logger = LogFactory.getLog(TYHttpClient.class);

    public TYHttpClient() {
        super();
    }

    private TYHttpClient(ClientConfig clientConfig) {
        super(clientConfig);
    }

    public static TYHttpClient getInstance() {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TYHttpClient.class) {
                if (instance == null) {
                    instance = new TYHttpClient();
                }

                return instance;
            }
        }
    }

    public static TYHttpClient getInstance(ClientConfig clientConfig) {
        if (instance != null) {
            return instance;
        } else {
            synchronized (TYHttpClient.class) {
                if (instance == null) {
                    instance = new TYHttpClient(clientConfig);
                }

                return instance;
            }
        }
    }

    public static List<NameValuePair> requestMapToParams(Map<String, Object> params) {
        return TuyaHttpClientHelper.paramsConverter(params);
    }

    public String doGet(String url) {
        return this.doGet(url, (List) null, (List) null, (Charset) null, (RequestConfig) null);
    }

    public String doGet(String url, List<Header> header, Charset CHARSET) {
        return this.doGet(url, (List) null, header, CHARSET, (RequestConfig) null);
    }

    public String doGet(String url, List<NameValuePair> params, List<Header> header, Charset CHARSET, RequestConfig config) {
        HttpGet httpGet = new HttpGet(this.buildUri(url, params));
        httpGet = this.initHttpGetRequest(httpGet, config, header);

        String result = "";
        try {
            CloseableHttpResponse httpResponse = this.httpClient.execute(httpGet);
            validHttpStatus(httpResponse);
            result = this.getResultContent(httpResponse, CHARSET);
            httpResponse.close();
        } catch (TuyaClientException e) {
            httpGet.abort();
            throw new TuyaClientException(e.getMessage() + "; url is: " + url);
        } catch (IOException e1) {
            httpGet.abort();
            throw new RuntimeException(e1);
        } finally {
            httpGet.releaseConnection();
        }

        return result;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public InputStream doGetForStream(String url, List<NameValuePair> params, List<Header> header, RequestConfig config) {
        HttpGet httpGet = new HttpGet(this.buildUri(url, params));
        httpGet = this.initHttpGetRequest(httpGet, config, header);

        InputStream result;
        try {
            CloseableHttpResponse httpResponse = this.httpClient.execute(httpGet);
            validHttpStatus(httpResponse);
            result = httpResponse.getEntity().getContent();
            httpResponse.close();
        } catch (TuyaClientException e) {
            httpGet.abort();
            throw new TuyaClientException(e.getMessage() + "; url is: " + url);
        } catch (IOException var13) {
            httpGet.abort();
            throw new RuntimeException(var13);
        } finally {
            httpGet.releaseConnection();
        }

        return result;
    }

    public String doPost(String url, List<NameValuePair> params) {
        return this.doPost(url, params, (Object) null, (List) null, (Charset) null, (RequestConfig) null);
    }

    public String doPost(String url, List<NameValuePair> params, List<Header> header) {
        return this.doPost(url, params, (Object) null, header, (Charset) null, (RequestConfig) null);
    }

    public String postJson(String url, String xmlData, RequestConfig config, List<Header> header, Charset CHARSET) {
        if (header == null || ((List) header).isEmpty()) {
            header = new ArrayList();
        }

        return this.doPost(url, (List) null, xmlData, (List) header, CHARSET, config);
    }

    public String doPost(String url, List<NameValuePair> params, Object postData, List<Header> header, Charset CHARSET, RequestConfig config) {
        HttpPost httpPost = new HttpPost(this.buildUri(url, params));
        httpPost = this.initHttpPostRequest(httpPost, config, header);
        if (CHARSET == null) {
            CHARSET = Consts.UTF_8;
        }

        String result = "";
        try {
            if (postData != null) {
                if (postData instanceof String && StringUtils.isNotBlank(String.valueOf(postData))) {
                    StringEntity strinEntity = new StringEntity(String.valueOf(postData), CHARSET);
                    httpPost.setEntity(strinEntity);
                } else if (postData instanceof Map) {
                    Map<String, Object> object = (Map) postData;
                    if (object != null && !object.isEmpty()) {
                        httpPost.setEntity(new UrlEncodedFormEntity(TuyaHttpClientHelper.paramsConverter(object), CHARSET));
                    }
                } else {
                    logger.error("异常了");
                }
            }

            CloseableHttpResponse httpResponse = this.httpClient.execute(httpPost);
            validHttpStatus(httpResponse);
            result = this.getResultContent(httpResponse, CHARSET);
            httpResponse.close();
        } catch (TuyaClientException e) {
            httpPost.abort();
            throw new TuyaClientException(e.getMessage() + "; url is: " + url);
        } catch (IOException e) {
            httpPost.abort();
            throw new RuntimeException(e);
        } finally {
            httpPost.releaseConnection();
        }

        return result;
    }


    public String doDelete(String url, List<Header> header, Charset CHARSET, RequestConfig config) {
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete = this.initHttpDeleteRequest(httpDelete, config, header);
        if (CHARSET == null) {
            CHARSET = Consts.UTF_8;
        }
        String result = "";
        try {
            CloseableHttpResponse response = this.httpClient.execute(httpDelete);
            validHttpStatus(response);
            result = this.getResultContent(response, CHARSET);
            response.close();
        } catch (TuyaClientException e) {
            httpDelete.abort();
            throw new TuyaClientException(e.getMessage() + "; url is: " + url);
        } catch (IOException e) {
            httpDelete.abort();
            throw new RuntimeException(e);
        } finally {
            httpDelete.releaseConnection();
        }
        return result;
    }

    public String doPut(String url, List<Header> header, Object postData, Charset CHARSET, RequestConfig config){
        HttpPut httpPut = new HttpPut(url);
        httpPut = this.initHttpPutRequest(httpPut, config, header);
        if (CHARSET == null) {
            CHARSET = Consts.UTF_8;
        }
        String result = "";
        try {
            if (postData != null) {
                if (postData instanceof String && StringUtils.isNotBlank(String.valueOf(postData))) {
                    StringEntity strinEntity = new StringEntity(String.valueOf(postData), CHARSET);
                    httpPut.setEntity(strinEntity);
                } else if (postData instanceof Map) {
                    Map<String, Object> object = (Map) postData;
                    if (object != null && !object.isEmpty()) {
                        httpPut.setEntity(new UrlEncodedFormEntity(TuyaHttpClientHelper.paramsConverter(object), CHARSET));
                    }
                } else {
                    logger.error("异常了");
                }
            }

            CloseableHttpResponse httpResponse = this.httpClient.execute(httpPut);
            validHttpStatus(httpResponse);
            result = this.getResultContent(httpResponse, CHARSET);
            httpResponse.close();
        } catch (TuyaClientException e) {
            httpPut.abort();
            throw new TuyaClientException(e.getMessage() + "; url is: " + url);
        } catch (IOException e) {
            httpPut.abort();
            throw new RuntimeException(e);
        } finally {
            httpPut.releaseConnection();
        }
        return result;
    }

    /**
     * 验证请求
     *
     * @param httpResponse
     * @return
     */
    private void validHttpStatus(CloseableHttpResponse httpResponse) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new TuyaClientException("HttpClient,error status code :" + statusCode);
        }
    }




}
