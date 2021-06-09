package com.yd.scala.dubbo.provider;

import com.yd.scala.dubbo.client.ITestService;
import com.yd.scala.dubbo.client.domain.BooleanVO;
import org.apache.dubbo.config.annotation.Service;

import java.util.Random;

/**
 * @author created by Zeb灬D on 2020-12-04 15:30
 */
@Service
public class TestService implements ITestService {
    @Override
    public BooleanVO test(String name, Boolean success) {
        BooleanVO vo = new BooleanVO();
        vo.setName(System.currentTimeMillis() + name);
        vo.setSuccess(success);
        vo.setDefault(false);
        vo.setAge(new Random().nextInt());
        vo.setA(Integer.valueOf(1));
        return vo;
    }
}
