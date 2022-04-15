package com.yd.api.moudle;

import com.yd.api.model.BaseInfo;
import com.yd.api.model.Request;
import com.yd.api.model.enums.HttpMethod;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.List;
import java.util.Map;

/**
 * 公共接口模块
 *
 *
 * @date: 2019-01-07 21:50
 */
public class Common extends Oauth{

    private BaseInfo baseInfo;

    public Common(BaseInfo baseInfo) {
        super(baseInfo);
        this.baseInfo = baseInfo;
    }

    /**
     * 万能涂鸦接口
     *
     * @param url
     * @param method
     * @param body
     * @return
     */
    public String commonHttpRequest(String url, HttpMethod method, Map<String, String> headers, Object body) {
        List<Header> listHeaders = getHeader(false, this.baseInfo);
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                String value = headers.get(key);
                listHeaders.add(new BasicHeader(key, value));
            }
        }
        Request request = new Request(url, method, headers, false, body, baseInfo);
        return sendHttpRequestWithOauthToken(request);
    }
}
