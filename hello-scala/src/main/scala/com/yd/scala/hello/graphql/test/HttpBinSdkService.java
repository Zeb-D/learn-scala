package com.yd.scala.hello.graphql.test;

import com.yd.scala.hello.sdk.HttpMethod;
import com.yd.scala.hello.sdk.SdkHttp;

/**
 * @author created by Zeb灬D on 2021-09-15 16:07
 */
public interface HttpBinSdkService {
    @SdkHttp(path = "/anything", method = HttpMethod.GET)
    HttpBinResponse anythingGet(Byte b, Short s, Integer i, Long l, String str);
}
