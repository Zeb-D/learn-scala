package com.yd.scala.webflux.dao;

import com.yd.scala.webflux.domian.Post;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author created by Zeb-D on 2019-06-10 20:27
 */
@Component
public class PostRedisRepository {

    ReactiveRedisOperations<String, Post> template;

    public PostRedisRepository(ReactiveRedisOperations<String, Post> template) {
        this.template = template;
    }

    public Flux<Post> findAll() {
        return template.<String, Post>opsForHash().values("posts");
    }

    public Mono<Post> findById(String id) {
        return template.<String, Post>opsForHash().get("posts", id);
    }

    public Mono<Post> save(Post post) {
        if (post.getId() != null) {
            String id = UUID.randomUUID().toString();
            post.setId(id);
        }
        return template.<String, Post>opsForHash().put("posts", post.getId(), post)
                .log()
                .map(p -> post);

    }

    public Mono<Void> deleteById(String id) {
        return template.<String, Post>opsForHash().remove("posts", id)
                .flatMap(p -> Mono.<Void>empty());
    }

    public Mono<Boolean> deleteAll() {
        return template.<String, Post>opsForHash().delete("posts");
    }

}
