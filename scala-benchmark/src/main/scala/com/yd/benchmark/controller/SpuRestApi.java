package com.yd.benchmark.controller;

import com.yd.benchmark.domain.Spu;
import com.yd.benchmark.service.SpuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * 使用wrk 进行压测
 * wrk -t10 -c100 -d120s --timeout 2s --latency 'http://localhost:8080/v1/spu/detail?id=1'
 */
@RestController
@Slf4j
public class SpuRestApi {
  
  @Autowired
  SpuService spuService;
  
  @GetMapping("/v1/spu/detail")
  public Mono<Spu> findById(Long id){
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    return Mono.just(id)
        .flatMap(x-> Mono.fromCallable(()-> spuService.findSpuById(x))
        .subscribeOn(Schedulers.elastic())) //关键，netty线程不适合用来处理长时间阻塞的任务
        .doOnSuccess(x-> {
          stopWatch.stop();
          log.info("{}", stopWatch.toString());
        });
  }
}