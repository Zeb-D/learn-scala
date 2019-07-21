package com.yd.api.internal;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;

public class TuyaHttpClientHelper {

    public TuyaHttpClientHelper() {
    }

    public static List<NameValuePair> paramsConverter(Map<String, Object> params) {
        List<NameValuePair> nvps = new LinkedList();
        Set<Map.Entry<String, Object>> paramsSet = params.entrySet();
        Iterator var3 = paramsSet.iterator();

        while(var3.hasNext()) {
            Map.Entry<String, Object> paramEntry = (Map.Entry)var3.next();
            nvps.add(new BasicNameValuePair((String)paramEntry.getKey(), paramEntry.getValue().toString()));
        }

        return nvps;
    }
}