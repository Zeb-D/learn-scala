package com.yd.benchmark.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * @author created by Zeb灬D on 2020-04-25 14:48
 */
@Component
@Aspect
@Slf4j
public class PermAspect {

    @Pointcut("@annotation(com.yd.benchmark.annotation.Perm)")
    public void perm() {
    }

    @Before("perm()")
    public void beforeAtopServiceMethodInvoke(JoinPoint point) {
        MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) point;
        log.info("className:{},method:{},args:{}", point.getTarget().getClass(), methodPoint.toLongString(), methodPoint.getArgs().length);
    }

    @Around("perm()")
    public Object arounInvoke(ProceedingJoinPoint point) throws Throwable {
        try {
            Object result = point.proceed();
            log.info(result.getClass().getSimpleName());
            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
    }
}

