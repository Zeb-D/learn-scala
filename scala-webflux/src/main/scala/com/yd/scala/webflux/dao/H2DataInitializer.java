package com.yd.scala.webflux.dao;

import com.yd.scala.webflux.domian.Post;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * @author created by zouyd on 2019-06-10 20:17
 */
@Component
public class H2DataInitializer {

    private final RxJava2PostRepository posts;

    public H2DataInitializer(RxJava2PostRepository posts) {
        this.posts = posts;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void initPosts() {
        System.out.println("initializing posts data...");
        Stream.of("bianzhaoyu", "xinan").forEach(
                name -> this.posts.save(new Post(LocalDate.now().toString(), name))
                        .subscribe()
        );
    }
}
