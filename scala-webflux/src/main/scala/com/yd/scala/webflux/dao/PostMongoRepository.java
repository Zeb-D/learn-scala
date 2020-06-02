package com.yd.scala.webflux.dao;

import com.yd.scala.webflux.domian.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author created by Zeb-D on 2019-06-10 19:56
 */
public interface PostMongoRepository extends ReactiveMongoRepository<Post, String> {
}
