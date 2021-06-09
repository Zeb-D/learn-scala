package com.yd.scala.dubbo.client;

import com.yd.scala.dubbo.client.domain.BooleanVO;

/**
 * @author created by Zeb灬D on 2020-12-04 15:29
 */
public interface ITestService {
    BooleanVO test(String name, Boolean success);
}
