package com.yd.scala.hello.sdk;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author created by Zeb灬D on 2021-07-23 15:51
 */
@Setter
@Getter
public class SdkScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    private Class<SdkFactoryBean> factoryBean;
    private String basePackage;

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
