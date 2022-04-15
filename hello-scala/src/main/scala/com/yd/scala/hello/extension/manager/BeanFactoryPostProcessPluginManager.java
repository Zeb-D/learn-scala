package com.yd.scala.hello.extension.manager;

import com.yd.scala.hello.extension.spring.KitApplicationListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zeb灬D
 * @date 2021/5/21 6:36 下午
 */

public class BeanFactoryPostProcessPluginManager {
    private static final List<BeanFactoryPostProcessPlugin> plugins = new LinkedList<>();

    public static void addPlugin(BeanDefinitionRegistry registry, BeanFactoryPostProcessPlugin postProcessor) {
        plugins.add(postProcessor);
        SingleBeanRegisterManager.register(registry, "kitApplicationListener", KitApplicationListener.class);
    }

    public static void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (BeanFactoryPostProcessPlugin plugin : plugins) {
            plugin.postProcessBeanFactory(beanFactory);
        }
    }
}
