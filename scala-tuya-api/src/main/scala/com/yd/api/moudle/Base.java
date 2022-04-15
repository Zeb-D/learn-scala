package com.yd.api.moudle;

import com.yd.api.exception.TuyaClientException;
import com.yd.api.model.BaseInfo;
import com.yd.api.model.Request;
import com.yd.api.model.enums.HttpMethod;
import com.yd.api.util.HMACUtil;
import com.yd.api.util.TYHttpClient;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基础模块
 *
 *
 * @date: 2019-01-07 20:46
 */
public class Base {

    // 编码
    public final Charset charset = Charset.forName("UTF-8");

    /**
     * 发送http请求
     * @param request
     * @return
     */
    public String sendHttpRequest(Request request) {
        StringBuilder sb = new StringBuilder("");
        String result = "-1";
        HttpMethod method = request.getMethod();
        Map<String, String>  urlParams = request.getUrlParams();
        String url = request.getUrl();
        Boolean isToken = request.getToken();
        sb.append(request.getUrl());
        Long time = System.currentTimeMillis();
        BaseInfo baseInfo = request.getBaseInfo();
        switch (method){
            case POST:{
                // 替代中间参数
                if(urlParams != null){
                    for (String key : urlParams.keySet()) {
                        //{var} ,大括号之间支持空格
                        url = url.replace("{" + key + "}", urlParams.get(key));
                    }
                }
                result = TYHttpClient.getInstance().doPost(sb.toString(), null,  request.getBody(), getHeader(isToken, baseInfo), charset, null);
                break;
            }
            case GET:{
                if (urlParams != null) {
                    sb.append("?");
                    //业务参数拼装
                    for (String key : urlParams.keySet()) {
                        sb.append(key).append("=").append(urlParams.get(key)).append("&");
                    }
                    sb.deleteCharAt(sb.length()-1);
                }
                result = TYHttpClient.getInstance().doGet(sb.toString(), getHeader(isToken, baseInfo), charset);
                break;
            }
            case DELETE:{
                result = TYHttpClient.getInstance().doDelete(sb.toString(), getHeader(isToken, baseInfo), charset, null);
                break;
            }
            default:{
                throw new TuyaClientException("current no this method function: " + method);
            }
        }
//        Long lastTime = System.currentTimeMillis() - time;
//        // 临时测试代码
//        // ############
//        logger.info("调用时间：" + lastTime + "ms");
//        // ############
        return result;
    }


    /**
     * 获取Http请求所需要的请求头
     *
     * @return
     */
    public List<Header> getHeader(Boolean isToken, BaseInfo baseInfo) {
        String clientId = baseInfo.getClientId();
        String sercet = baseInfo.getSecret();
        String accessToken = baseInfo.getAccessToken();
        //header参数
        List<Header> headers = new ArrayList<Header>();
        Header contentHeader = new BasicHeader("Content-Type", baseInfo.getContentType());
        Header clientHeader = new BasicHeader("client_id", clientId);
        Header signMethodHeader = new BasicHeader("sign_method", "HMAC-SHA256");
        Long time = System.currentTimeMillis();
        Header timeHeader = new BasicHeader("t", time.toString());
        if (isToken) {
//            Header signHeader = new BasicHeader("sign", MD5Util.getMD5(clientId + sercet +
//                    time.toString()).toUpperCase());
            Header signHeader = new BasicHeader("sign", HMACUtil.HMAC(clientId + time.toString(), sercet).toUpperCase());
            headers.add(signHeader);
        } else {
            Header accessHeader = new BasicHeader("access_token", accessToken);
//            Header signHeader = new BasicHeader("sign", MD5Util.getMD5(clientId + accessToken + sercet +
//                    time.toString()).toUpperCase());
            Header signHeader = new BasicHeader("sign", HMACUtil.HMAC(clientId + accessToken + time.toString(), sercet).toUpperCase());
            headers.add(accessHeader);
            headers.add(signHeader);
        }
        headers.add(clientHeader);
        headers.add(contentHeader);
        headers.add(timeHeader);
        headers.add(signMethodHeader);
        return headers;
    }

}
