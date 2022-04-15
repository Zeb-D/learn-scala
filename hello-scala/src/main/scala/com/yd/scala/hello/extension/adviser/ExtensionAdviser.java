package com.yd.scala.hello.extension.adviser;

import com.yd.scala.hello.extension.annotation.ExtensionPoint;
import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Zeb灬D
 * @date 2021/5/20 12:10 下午
 */

@Aspect
@EnableAspectJAutoProxy
public class ExtensionAdviser extends AbstractPointcutAdvisor {

    private static final long serialVersionUID = 8884816708583520021L;

    private final ExtensionMethodInterceptor extensionMethodInterceptor = new ExtensionMethodInterceptor();

    @Override
    public Pointcut getPointcut() {
        return new AnnotationMatchingPointcut(null, ExtensionPoint.class);
    }

    @Override
    public Advice getAdvice() {
        return this.extensionMethodInterceptor;
    }
}