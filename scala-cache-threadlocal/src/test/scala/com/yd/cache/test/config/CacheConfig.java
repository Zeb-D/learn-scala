package com.yd.cache.test.config;

import com.yd.cache.threadlocal.ThreadLocalCacheBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author created by Zeb灬D on 2020-06-05 21:57
 */
@Configuration
public class CacheConfig {
    @Bean
    public ThreadLocalCacheBeanPostProcessor threadLocalCachePostProcessor() {

        ThreadLocalCacheBeanPostProcessor postProcessor = new ThreadLocalCacheBeanPostProcessor();
        postProcessor.setExpressions(Arrays.asList(
                "execution(public * com.yd.cache.test.service..*(..))"));
        return postProcessor;

    }
}
