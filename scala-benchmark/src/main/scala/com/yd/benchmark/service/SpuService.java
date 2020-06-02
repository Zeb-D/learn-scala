package com.yd.benchmark.service;

import com.yd.benchmark.annotation.Perm;
import com.yd.benchmark.domain.Spu;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SpuService {

    @Perm
    public Spu findSpuById(Long id) {
        try {
            TimeUnit.MILLISECONDS.sleep(50); //模拟耗时任务
        } catch (InterruptedException e) {
        }
        Spu s = new Spu().setId(id).setName("spu" + id);
        return s;
    }
}