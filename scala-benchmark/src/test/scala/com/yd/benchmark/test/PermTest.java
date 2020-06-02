package com.yd.benchmark.test;

import com.yd.benchmark.convert.SpuMapper;
import com.yd.benchmark.domain.Spu;
import com.yd.benchmark.service.SpuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author created by Zeb灬D on 2020-04-25 15:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermTest {
    @Resource
    private SpuService spuService;

    @Test
    public void test() {
        Spu s = spuService.findSpuById(1L);
        System.out.println(SpuMapper.INSTANCE.toSpuRes(s));
    }
}
