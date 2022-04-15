package com.yd.scala.hello.extension.manager;

import com.yd.scala.hello.extension.spring.KitSpringContext;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author Zeb灬D
 * @date 2021/5/22 7:56 上午
 */

public class SingleBeanRegisterManager {

    public static void register(BeanDefinitionRegistry beanDefinitionRegistry, String beanName, Class<?> beanClass) {
        if (beanDefinitionRegistry == null) {
            beanDefinitionRegistry = KitSpringContext.getBeanDefinitionRegistry();
        }

        if (!beanDefinitionRegistry.containsBeanDefinition(beanName)) {
            GenericBeanDefinition gbd = new GenericBeanDefinition();
            gbd.setBeanClass(beanClass);
            beanDefinitionRegistry.registerBeanDefinition(beanName, gbd);
        }
    }
}
