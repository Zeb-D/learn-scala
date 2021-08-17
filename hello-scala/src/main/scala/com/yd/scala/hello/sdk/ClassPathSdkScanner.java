package com.yd.scala.hello.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * @author created by Zeb灬D on 2021-07-23 14:35
 */
@Slf4j
public class ClassPathSdkScanner extends ClassPathBeanDefinitionScanner {
    private Class factoryBean;

    public ClassPathSdkScanner(Class factoryBan, BeanDefinitionRegistry registry) {
        super(registry);
        this.factoryBean = factoryBan;
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> holders = super.doScan(basePackages);
        holders.stream().forEach(holder -> {
            GenericBeanDefinition beanDefinition = (GenericBeanDefinition) holder.getBeanDefinition();
            String beanClassName = beanDefinition.getBeanClassName();
            log.info("sdk proxy beanName:{}", beanClassName);
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            beanDefinition.setBeanClass(factoryBean);
        });
        return holders;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    public void registerFilters() {
        addIncludeFilter((k, v) -> true);
        addExcludeFilter((metadataReader, v)
                -> metadataReader.getClassMetadata().getClassName().endsWith("package-info"));
    }
}
