package com.yd.test.validate;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Component
@Scope("singleton")
public class TestBean implements BeanPostProcessor, InstantiationAwareBeanPostProcessor,
        SmartInitializingSingleton,
        BeanFactoryAware,
        InitializingBean, DisposableBean {

    private static final String BEAN_NAME = "customBean";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            System.out.println("==>BeanPostProcessor.postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            System.out.println("==>BeanPostProcessor.postProcessAfterInitialization");
        }
        return bean;
    }


    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("==>SmartInitializingSingleton.afterSingletonsInstantiated");

    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            System.out.println("==>InstantiationAwareBeanPostProcessor.postProcessBeforeInstantiation");
        }
        return beanClass;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (BEAN_NAME.equals(beanName)) {
            System.out.println("==>InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("==>InstantiationAwareBeanPostProcessor.postProcessPropertyValues");
        return pvs;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("==>DisposableBean.destroy");
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        System.out.println("BeanFactoryAware#setBeanFactory");

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet");
    }

}