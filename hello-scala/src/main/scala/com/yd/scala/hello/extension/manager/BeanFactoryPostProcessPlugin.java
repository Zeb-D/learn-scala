package com.yd.scala.hello.extension.manager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Zeb灬D
 * @date 2021/5/21 6:55 下午
 */

public interface BeanFactoryPostProcessPlugin {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
