package com.yd.scala.hello.sdk;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author created by Zeb灬D on 2021-07-23 14:55
 */
public class SdkScannerRegistrar implements ImportBeanDefinitionRegistrar, BeanDefinitionRegistryPostProcessor {
    private AnnotationAttributes annoAttrs;
    @Setter
    @Getter
    private Class<SdkFactoryBean> factoryBean;
    @Setter
    @Getter
    private String basePackage;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        annoAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(SdkScanner.class.getName()));

        if (annoAttrs != null) {
            //注入扫描的配置信息配置
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SdkScannerRegistrar.class);
            builder.addPropertyValue("factoryBean", annoAttrs.get("factoryBean"));
            builder.addPropertyValue("basePackage", annoAttrs.get("basePackage"));
            String beanName = importingClassMetadata.getClassName() + "#" + SdkScannerRegistrar.class.getName();
            registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathSdkScanner scanner = new ClassPathSdkScanner(factoryBean, registry);
        scanner.registerFilters();
        scanner.scan(basePackage);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
