package com.yd.scala.hello.handler.aspect;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author Zeb灬D
 * @date 2021/5/20 12:10 下午
 */

@Aspect
@EnableAspectJAutoProxy
@Component
public class ZebraAdviser extends AbstractPointcutAdvisor {

    private static final long serialVersionUID = 8884816708583520021L;

    private final ZebraInterceptor zebraInterceptor = new ZebraInterceptor();

    @Override
    public Pointcut getPointcut() {
        return new AnnotationMatchingPointcut(null, Zebra.class);
    }

    @Override
    public Advice getAdvice() {
        return this.zebraInterceptor;
    }
}