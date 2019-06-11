package com.yd.scala.webflux.dao;

import com.yd.scala.webflux.domian.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Component
@ConditionalOnProperty(name = "mongo.enable", havingValue = "true", matchIfMissing = true)
class MongoDataInitializer {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final PostMongoRepository posts;

    public MongoDataInitializer(PostMongoRepository posts) {
        this.posts = posts;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void run(String[] args) {
        log.info("start data initialization  ...");
        this.posts
                .deleteAll()
                .thenMany(
                        Flux
                                .just("bianzhaoyu", "xinan")
                                .flatMap(
                                        name -> this.posts.save(new Post(LocalDate.now().toString(), name))
                                )
                )
                .log()
                .subscribe(
                        null,
                        null,
                        () -> log.info("done initialization...")
                );

    }

}