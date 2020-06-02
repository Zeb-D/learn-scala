/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yd.scala.webflux;

import com.yd.scala.webflux.domian.Post;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.Part;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 在基础框架reactor中Mono代表一个单次发送的数据源，而Flux代表一个可多次发送的数据源。
 * 在spring webflux的controller中，Mono很好理解，代表前端的一次传参或接口的一次返回。
 * 接受Multipart参数、返回Stream类型数据或者用于分批返回
 *
 * @author created by Zeb-D on 2019-06-10 16:25
 */
@RestController
@RequestMapping("/uploads")
public class MultipartController {

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Mono<String> requestBodyFlux(@RequestBody Flux<Part> parts) {
        return parts.map(part -> part instanceof FilePart
                ? part.name() + ":" + ((FilePart) part).filename()
                : part.name()).next()
                ;
    }

    //如果不是application/stream json則呼叫端無法滾動得到結果，將一直阻塞等待資料流結束或超時。
    @GetMapping(value = "stream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Post> getBeanStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(l -> new Post("bian", LocalDateTime.now().toString()))
                .log();
    }
}

