package com.yd.scala.hello.sdk;

import org.springframework.beans.factory.FactoryBean;

/**
 * 目标：将此处的代码进行 interface 代理对象成sdk
 * 代码为：https://github.com/Zeb-D/my-java-api/blob/master/learn-others/src/main/java/com/yd/feign/FeignHelloServiceTest.java
 */
public class SdkFactoryBean<T> implements FactoryBean<T> {
    private Class<T> clazz;

    public SdkFactoryBean(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T getObject() throws Exception {
        return (T) SdkManager.newProxyInstance(clazz);
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
