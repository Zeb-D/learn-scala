package com.yd.cache.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 设置到的切点，最终在方法执行后，会自动清理本地缓存
 *
 * @author created by Zeb-D on 2019-05-08 14:37
 */
@Slf4j
public class ThreadLocalCacheMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } finally {
            log.info("Exec ThreadLocalCacheMethodInterceptor for {}", invocation.getMethod());
            ThreadLocalCacheContext.cleanUp();
        }
    }

}