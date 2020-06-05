package com.yd.cache.threadlocal;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * ThreadLocalCache 的 ThreadLocal 清理处理器。通过设置 N 个 AspectJ Pointcut 表达式来实现拦截处理。
 * 可以设置你需要的切点，执行切面的优先级
 * 注意：只有在切点里面 {@link ThreadLocalCacheBeanPostProcessor#expressions}，
 * 使用 {@link ThreadLocalCacheContext} 里面的本地缓存，
 * 最后执行完方法后会执行{@link ThreadLocalCacheContext#cleanUp }
 * 如果你不在切点使用{@link ThreadLocalCacheContext} 则需要你执行 {@link ThreadLocalCacheContext#cleanUp }
 *
 * @author created by Zeb-D on 2019-05-08 14:37
 */
@Slf4j
public class ThreadLocalCacheBeanPostProcessor extends AbstractAdvisingBeanPostProcessor implements InitializingBean {

    public ThreadLocalCacheBeanPostProcessor() {
        setProxyTargetClass(true);
    }

    @Setter
    private List<String> expressions;

    @Setter
    private Integer order;


    @Override
    public void afterPropertiesSet() {

        if (CollectionUtils.isEmpty(expressions)) {
            log.warn("Expressions is EMPTY.");
            return;
        }

        Pointcut pointcut = buildPointcut();

        if (pointcut == null) {
            log.warn("Pointcut is NULL.");
            return;
        }

        ThreadLocalCacheMethodInterceptor interceptor = new ThreadLocalCacheMethodInterceptor();

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, interceptor);
        advisor.setOrder(order == null ? Ordered.HIGHEST_PRECEDENCE : order);

        this.advisor = advisor;
    }

    private Pointcut buildPointcut() {
        ComposablePointcut composablePointcut = null;

        for (String expression : expressions) {
            if (StringUtils.isEmpty(expression)) {
                continue;
            }
            log.info("Expression ={}", expression);

            AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
            pointcut.setExpression(expression);
            if (composablePointcut == null) {
                composablePointcut = new ComposablePointcut((Pointcut) pointcut);
            } else {
                composablePointcut.union((Pointcut) pointcut);
            }

        }

        return composablePointcut;
    }

}