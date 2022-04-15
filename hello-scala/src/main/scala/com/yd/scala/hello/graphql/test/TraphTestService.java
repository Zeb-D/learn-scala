package com.yd.scala.hello.graphql.test;

import org.springframework.stereotype.Service;


@Service
public class TraphTestService implements ITraphTestService {
    @Override
    public TraphResult traphService(String phone) {
        TraphResult result = new TraphResult();
        result.setBb((byte) 1);
        result.setSs((short) 2);
        result.setIi(3);
        result.setDd(4.0D);
        result.setFf(5.0F);
        result.setLl(6L);

        result.setUserName("YiTian");
        result.setAge(119);
        result.setAddr("MangoNet");
        result.setPhone(phone);
        return result;
    }
}
