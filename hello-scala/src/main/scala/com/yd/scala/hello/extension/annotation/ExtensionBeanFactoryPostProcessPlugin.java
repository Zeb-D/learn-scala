package com.yd.scala.hello.extension.annotation;

import com.yd.scala.hello.extension.config.BaseConfig;
import com.yd.scala.hello.extension.config.ExtensionPointDefinition;
import com.yd.scala.hello.extension.manager.BeanFactoryPostProcessPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * @author Zeb灬D
 * @date 2021/5/22 8:25 上午
 */

public class ExtensionBeanFactoryPostProcessPlugin implements BeanFactoryPostProcessPlugin {
    private static final Logger logger = LoggerFactory.getLogger(ExtensionBeanFactoryPostProcessPlugin.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final Iterator<String> beanNameIterator = beanFactory.getBeanNamesIterator();
        ClassLoader classLoader = beanFactory.getBeanClassLoader();
        while (beanNameIterator.hasNext()) {
            String beanName = beanNameIterator.next();
            BeanDefinition beanDefinition;
            try {
                beanDefinition = beanFactory.getBeanDefinition(beanName);
            } catch (Exception e) {
                continue;
            }
            if (!(beanDefinition instanceof AnnotatedBeanDefinition)
                    || !(beanDefinition instanceof AbstractBeanDefinition)) {
                continue;
            }
            AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
            AnnotationMetadata annotationMetadata = annotatedBeanDefinition.getMetadata();
            if (!annotationMetadata.hasAnnotatedMethods(ExtensionPoint.class.getName())) {
                continue;
            }
            Class<?> beanClass = getBeanClass((AbstractBeanDefinition) beanDefinition, classLoader);
            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                ExtensionPoint extensionPoint = method.getAnnotation(ExtensionPoint.class);
                if (extensionPoint == null) {
                    continue;
                }
                ExtensionPointDefinition definition = new ExtensionPointDefinition();
                definition.setBeanName(beanName);
                definition.setClassName(beanClass.getName());
                definition.setExtensionPointName(extensionPoint.name());
                definition.setMethodName(method.getName());
                definition.setReturnType(method.getGenericReturnType());
                definition.setParameterTypes(method.getParameterTypes());
                BaseConfig.addExtensionPointDefinition(definition);
            }
        }
    }

    private Class<?> getBeanClass(AbstractBeanDefinition beanDefinition, ClassLoader classLoader) {
        if (!beanDefinition.hasBeanClass()) {
            try {
                beanDefinition.resolveBeanClass(classLoader);
            } catch (Exception exception) {
                logger.error("resolve bean class error", exception);
            }
        }
        return beanDefinition.getBeanClass();
    }
}
