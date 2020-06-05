package com.yd.cache.test;

import com.yd.cache.test.config.PrintLocalCacheLoader;
import com.yd.cache.test.service.HelloService;
import com.yd.cache.threadlocal.ThreadLocalCacheContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author created by Zeb灬D on 2020-04-25 15:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheTest {
    @Autowired
    private HelloService helloService;

    @Test
    public void test() {
        String localName = ThreadLocalCacheContext.get(PrintLocalCacheLoader.class.getName(), "Yd" );
        System.out.println("1------->"+localName);
        System.out.println(helloService.sayHello("Yd"));
    }
}
