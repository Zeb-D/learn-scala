package com.yd.sdk.smart;

import com.yd.sdk.smart.config.ClientConfig;
import com.yd.sdk.smart.internal.AtopThirdCloudMobileSignUtil;
import com.yd.sdk.smart.internal.AtopThirdMobileSignUtil;
import com.yd.sdk.smart.internal.CloudResponseHandler;
import com.yd.sdk.smart.internal.TuyaHttpClientFactory;
import com.yd.sdk.smart.model.RequestMessage;
import com.yd.sdk.smart.model.ResponseMessage;
import com.yd.sdk.smart.util.AtopMobileSignUtil;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class TuyaCloudClient {

    private String accessId;
    private String accessKey;
    private String endUri;
    private ClientConfig clientConfig;
    private CloseableHttpClient httpclient;


    public TuyaCloudClient(String accessId, String accessKey,String endUri) {
        this.accessId = accessId;
        this.accessKey = accessKey;
        this.endUri = endUri;
        this.clientConfig = new ClientConfig();
        this.httpclient = TuyaHttpClientFactory.getInstance().getDefaultClient(clientConfig); 
    }

    public TuyaCloudClient(String accessId, String accessKey,String endUri, ClientConfig config) {
        this.accessId = accessId;
        this.accessKey = accessKey;
        this.endUri = endUri;
        this.clientConfig = config;
        this.httpclient = TuyaHttpClientFactory.getInstance().getDefaultClient(clientConfig); 
    }

    public ResponseMessage sendRequest(RequestMessage request) {

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("postData", request.getPostData()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        String url = endUri +"?" + requestToQueryStr(request);
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        System.out.println();
        System.out.println(url+"&postData="+request.getPostData());
        System.out.println();

        CloudResponseHandler handler = new CloudResponseHandler();
            
        try {
            ResponseMessage response = httpclient.execute(httppost, handler);
            return response;
        } catch (Exception e) {
            System.out.println("执行http请求出错");
            e.printStackTrace();
        }finally {
            httppost.releaseConnection();
        }
        return null;
    }

    public String requestToQueryStr(RequestMessage request) {
        StringBuilder sb = new StringBuilder("");

        sb.append("a=").append(request.getApi()).append("&");
        sb.append("time=").append(request.getTime()).append("&");

        if (request.getSession() != null ) {
            sb.append("sid=").append(request.getSession()).append("&");
        }

        sb.append("lang=").append(request.getLang()).append("&");

        sb.append("v=").append(request.getApiVersion()).append("&");

        if (request.getOs() != null ) {
            sb.append("os=").append(request.getOs()).append("&");
        }

        if (request.getDeviceid() != null ) {
            sb.append("deviceId=").append(request.getDeviceid()).append("&");
        }

        sb.append("clientid=").append(this.accessId).append("&");

//        sb.append("postData=").append(this.accessId).append("&");

        request.setClientId(this.accessId);

        if ("app".equals(request.getType())){
            //生成签名
            String sign= AtopThirdMobileSignUtil.getSign(request, this.accessKey);
            sb.append("sign=").append(sign);
        } else if ("tuya".equals(request.getType())){
            //生成签名
            String sign= AtopMobileSignUtil.getSign(request, this.accessKey);
            System.out.println("signed="+sign);
            sb.append("sign=").append(sign);
        } else {
            //生成签名
            String sign=AtopThirdCloudMobileSignUtil.getSign(request, this.accessKey);
            System.out.println("signed="+sign);
            sb.append("sign=").append(sign);
        }


        return sb.toString();
    }



}
