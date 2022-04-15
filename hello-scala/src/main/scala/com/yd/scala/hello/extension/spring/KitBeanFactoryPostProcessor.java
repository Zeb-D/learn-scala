package com.yd.scala.hello.extension.spring;

import com.yd.scala.hello.extension.manager.BeanFactoryPostProcessPluginManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;


public final class KitBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanFactoryPostProcessPluginManager.postProcessBeanFactory(beanFactory);
    }
}
