package com.yd.cache.test.service;

import com.yd.cache.test.config.PrintLocalCacheLoader;
import com.yd.cache.threadlocal.ThreadLocalCacheContext;
import org.springframework.stereotype.Service;

/**
 * @author created by Zeb灬D on 2020-06-05 21:58
 */
@Service
public class HelloService {

    public String sayHello(String name) {
        String localName = ThreadLocalCacheContext.get(PrintLocalCacheLoader.class.getName(), "Yd" + name);
        String localName2 = ThreadLocalCacheContext.get(PrintLocalCacheLoader.class.getName(), "Yd" + name);
        if (localName == localName2) {
            System.out.println("成功命中本地缓存");
        }
        ThreadLocalCacheContext.cleanUp();
        localName = ThreadLocalCacheContext.get(PrintLocalCacheLoader.class.getName(), "Yd" + name);
        if (!localName.equals(localName2)) {
            System.out.println("没有成功命中本地缓存，因为清除过一次");
        }
        return name + System.currentTimeMillis();
    }
}
