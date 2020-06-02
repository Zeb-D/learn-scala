//package com.yd.scala.dubbo.aop;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * @author created by zouyd on 2019-05-08 14:43
// **/
//@Aspect
//@Order(0)
//@Component
//public class ProviderExceptionAspect {
//
//    private static Logger LOGGER = LoggerFactory.getLogger(ProviderExceptionAspect.class);
//
//    private static final String SYSTEM_ERROR_CODE = "SYSTEM_ERROR";
//    private static final String SYSTEM_ERROR_MESSAGE = "INTERNAL_ERROR";
//
//    @Pointcut("execution(* com.yd.scala.dubbo.provider..*.*(..))")
//    public void providerServiceMethod() {
//    }
//
//    @Around("providerServiceMethod()")
//    public Object atopServiceMethodProcessing(ProceedingJoinPoint point) throws Throwable {
//        try {
//            return point.proceed();
//        } catch (Exception ex) {
//            LOGGER.warn("", ex);
//            return null;
//        }
//    }
//
//}
