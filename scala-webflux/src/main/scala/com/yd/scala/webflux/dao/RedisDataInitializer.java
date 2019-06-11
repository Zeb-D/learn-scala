package com.yd.scala.webflux.dao;

import com.yd.scala.webflux.domian.Post;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

/**
 * @author created by zouyd on 2019-06-10 20:43
 */
@Component
public class RedisDataInitializer {

    private final PostRedisRepository posts;

    public RedisDataInitializer(PostRedisRepository posts) {
        this.posts = posts;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void run(String[] args) {
        System.out.println("start data initialization  ...");
        this.posts
                .deleteAll()
                .thenMany(
                        Flux
                                .just("bianzhaoyu", "xinan")
                                .flatMap(
                                        title -> {
                                            String id = UUID.randomUUID().toString();
                                            return this.posts.save(new Post(id, title));
                                        }
                                )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> System.out.println("done initialization...")
                );

    }
}
