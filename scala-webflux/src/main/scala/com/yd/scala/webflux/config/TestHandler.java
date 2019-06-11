package com.yd.scala.webflux.config;

import com.yd.scala.webflux.domian.Post;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class TestHandler {

    public Mono<ServerResponse> echoName(ServerRequest request) {
        return request.bodyToMono(Post.class)
                .map(Post::getName)
                .flatMap(name -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromObject("hello world!" + name)))
                ;
    }
}