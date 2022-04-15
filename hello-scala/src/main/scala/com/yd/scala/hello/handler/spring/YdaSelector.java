package com.yd.scala.hello.handler.spring;

import com.yd.scala.hello.handler.utils.DataPool;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class YdaSelector implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    private ResourceLoader resourceLoader;

    private Environment environment;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Set<AnnotationAttributes> annotationAttributes = new LinkedHashSet<>();
        addAttributesIfNotNull(annotationAttributes, importingClassMetadata.getAnnotationAttributes(EnableZebra.class.getName(), false));
        for (AnnotationAttributes componentScan : annotationAttributes) {
            boolean isZebra = componentScan.getBoolean("isZebra");
            DataPool.addStrategy("isZebra", isZebra);
        }
        ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(registry);
        classPathBeanDefinitionScanner.setResourceLoader(resourceLoader);
        classPathBeanDefinitionScanner.scan("com.yd.scala.hello.handler");
    }

    private void addAttributesIfNotNull(Set<AnnotationAttributes> result, Map<String, Object> attributes) {
        if (attributes != null) {
            result.add(AnnotationAttributes.fromMap(attributes));
        }
    }

}
