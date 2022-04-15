package com.yd.scala.hello.extension.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;


public final class KitSpringContext implements ApplicationContextAware, EnvironmentAware {
    private static volatile ApplicationContext applicationContext;

    private static volatile Environment environment;

    private static BeanDefinitionRegistry beanDefinitionRegistry;

    public static void setBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) {
        if (KitSpringContext.beanDefinitionRegistry == beanDefinitionRegistry) {
            return;
        }

        if (KitSpringContext.beanDefinitionRegistry != null) {
            throw new IllegalStateException("spring bean registry conflicts with existing");
        }

        KitSpringContext.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        KitSpringContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        KitSpringContext.environment = environment;
    }

    public static Environment getEnvironment() {
        return environment;
    }
}
