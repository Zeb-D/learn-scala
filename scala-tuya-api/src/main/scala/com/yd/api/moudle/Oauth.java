package com.yd.api.moudle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yd.api.exception.TuyaBizErrorCode;
import com.yd.api.exception.TuyaClientException;
import com.yd.api.model.BaseInfo;
import com.yd.api.model.Request;
import com.yd.api.model.TuyaResult;
import com.yd.api.model.domain.oauth.TokenResult;
import com.yd.api.model.enums.HttpMethod;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权模块
 *
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-07 20:44
 */
public class Oauth extends Base {

    private BaseInfo baseInfo;

    public Oauth(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    /**
     * 获取token
     *
     * @return
     */
    public void getToken() {
        String url = baseInfo.getBaseUrl() + "/v1.0/token";
        String code = baseInfo.getCode();
        // 模式
        Map<String, String> params = new HashMap<String, String>();
        // 简单模式
        if(StringUtils.isEmpty(code)){
            params.put("grant_type", "1");
        }else {
            params.put("grant_type", "2");
            params.put("code", code);
        }
        Request request = new Request(url, HttpMethod.GET, params, true, null, baseInfo);
        resetToken(request);
    }


    /**
     * 刷新token
     */
    public void getRefreshToken() {
        try {
            String url = baseInfo.getBaseUrl() + "/v1.0/token/" + baseInfo.getRefreshToken();
            Request request = new Request(url, HttpMethod.GET, null, true, null, baseInfo);
            resetToken(request);
        }catch (TuyaClientException e){
            // 说明从没有执行过getToken
            if(TuyaBizErrorCode.TOKEN_PARAM_ILLEGAL == e.getCode()){
                getToken();
            }else
                throw e;
        }
    }


    /**
     * 如果请求过期，所使用的二次请求
     *
     * @param request
     * @return
     */
    public TuyaResult resultIfTokenExpire(TuyaResult result, Request request) {
        Integer code = result.getCode();
        if (result.getSuccess()) {
            return result;
        } else if (code != null && 1010 == code) {
            // 更新token
            getToken();
            // 再次发送请求
            String msg = sendHttpRequest(request);
            TuyaResult tuyaResult = JSON.parseObject(msg, TuyaResult.class);
            return tuyaResult;
        }
        throw new TuyaClientException(result.getCode(), result.getMsg());
    }


    /**
     * 为http请求附加token
     *
     * @param request
     * @return
     */
    public String sendHttpRequestWithOauthToken(Request request) {
        String msg = this.sendHttpRequest(request);
        TuyaResult result = JSON.parseObject(msg, TuyaResult.class);
        result = this.resultIfTokenExpire(result, request);
        if(result.getSuccess())
            return result.getResult();
        throw new TuyaClientException(result.getCode(), result.getMsg());
    }


    public BaseInfo getBaseInfo() {
        return baseInfo;
    }


    private void resetToken(Request request) {
        String result = sendHttpRequest(request);
        TuyaResult tuyaResult = JSON.parseObject(result, TuyaResult.class);
        if (tuyaResult.getSuccess()) {
            TokenResult tokenResult = JSON.parseObject(tuyaResult.getResult(), TokenResult.class);
            baseInfo.setAccessToken(tokenResult.getAccessToken());
            baseInfo.setRefreshToken(tokenResult.getRefreshToken());
        } else {
            throw new TuyaClientException(tuyaResult.getCode(), tuyaResult.getMsg());
        }
    }

}
