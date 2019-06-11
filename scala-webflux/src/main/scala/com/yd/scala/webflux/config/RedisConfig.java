package com.yd.scala.webflux.config;

import com.yd.scala.webflux.controller.PostRedisHandler;
import com.yd.scala.webflux.dao.PostRedisRepository;
import com.yd.scala.webflux.domian.Post;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author created by zouyd on 2019-06-10 20:29
 */
@Configuration
@ConditionalOnProperty(name = "redis.enable", havingValue = "true", matchIfMissing = true)
public class RedisConfig {
    @Bean
    public ReactiveRedisTemplate<String, Post> reactiveJsonPostRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {

        RedisSerializationContext<String, Post> serializationContext = RedisSerializationContext
                .<String, Post>newSerializationContext(new StringRedisSerializer())
                .hashKey(new StringRedisSerializer())
                .hashValue(new Jackson2JsonRedisSerializer<>(Post.class))
                .build();


        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }

    @Bean
    public RouterFunction<ServerResponse> routes(PostRedisHandler postController) {
        return route(GET("/redis/posts"), postController::all)
                .andRoute(POST("/redis/posts"), postController::create)
                .andRoute(GET("/redis/posts/{id}"), postController::get)
                .andRoute(PUT("/redis/posts/{id}"), postController::update)
                .andRoute(DELETE("/redis/posts/{id}"), postController::delete);
    }
}
