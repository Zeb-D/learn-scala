package com.yd.scala.hello.editor.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class ConfigPropertySourcesProcessor extends PropertySourcesProcessor
        implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, PropertySourcesPlaceholderConfigurer.class.getName(),
                PropertySourcesPlaceholderConfigurer.class);
        BeanRegistrationUtil.registerBeanDefinitionIfNotExists(registry, ApolloAnnotationProcessor.class.getName(),
                ApolloAnnotationProcessor.class);
        processSpringValueDefinition(registry);
    }

    /**
     * For Spring 3.x versions, the BeanDefinitionRegistryPostProcessor would not be
     * instantiated if it is added in postProcessBeanDefinitionRegistry phase, so we have to manually
     * call the postProcessBeanDefinitionRegistry method of SpringValueDefinitionProcessor here...
     */
    private void processSpringValueDefinition(BeanDefinitionRegistry registry) {
    }
}