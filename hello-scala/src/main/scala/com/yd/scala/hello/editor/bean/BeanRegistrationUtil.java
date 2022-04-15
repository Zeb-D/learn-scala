package com.yd.scala.hello.editor.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.Objects;

public class BeanRegistrationUtil {
    public static boolean registerBeanDefinitionIfNotExists(BeanDefinitionRegistry registry, String beanName, Class<?> beanClass) {
        if (registry.containsBeanDefinition(beanName)) {
            return false;
        } else {
            String[] candidates = registry.getBeanDefinitionNames();
            String[] var4 = candidates;
            int var5 = candidates.length;

            for (String candidate : registry.getBeanDefinitionNames()) {
                BeanDefinition beanDefinition = registry.getBeanDefinition(candidate);
                if (Objects.equals(beanDefinition.getBeanClassName(), beanClass.getName())) {
                    return false;
                }
            }

            BeanDefinition annotationProcessor = BeanDefinitionBuilder.genericBeanDefinition(beanClass).getBeanDefinition();
            registry.registerBeanDefinition(beanName, annotationProcessor);
            return true;
        }
    }
}