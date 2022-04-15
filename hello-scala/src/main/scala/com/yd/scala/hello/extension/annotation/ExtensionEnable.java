
package com.yd.scala.hello.extension.annotation;

import com.yd.scala.hello.extension.adviser.ExtensionAdviser;
import com.yd.scala.hello.extension.config.BaseConfig;
import com.yd.scala.hello.extension.manager.ApplicationListenPluginManager;
import com.yd.scala.hello.extension.manager.BeanFactoryPostProcessPluginManager;
import com.yd.scala.hello.extension.manager.SingleBeanRegisterManager;
import com.yd.scala.hello.extension.spring.AbstractEnable;
import com.yd.scala.hello.extension.spring.KitApplicationListener;
import com.yd.scala.hello.extension.spring.KitBeanFactoryPostProcessor;
import com.yd.scala.hello.extension.spring.KitSpringContext;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created By Zeb灬D On 2021-05-25
 **/

public final class ExtensionEnable extends AbstractEnable implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        String appName = getAnnotationStringAttribute(environment
                , importingClassMetadata
                , EnableExtension.class.getName()
                , "group");

        BaseConfig.setAppName(appName);

        String definitionFactoryBeanName = getAnnotationStringAttribute(environment
                , importingClassMetadata
                , EnableExtension.class.getName()
                , "definitionFactory");

        BaseConfig.setExtensionDefinitionFactoryName(definitionFactoryBeanName);
        if (StringUtils.isBlank(definitionFactoryBeanName)) {
            Class<?> definitionFactoryClass = getAnnotationClassAttribute(environment
                    , importingClassMetadata
                    , EnableExtension.class.getName()
                    , "definitionFactoryClass");

            BaseConfig.setExtensionDefinitionFactoryClass(definitionFactoryClass);
        }

        KitSpringContext.setBeanDefinitionRegistry(registry);
        SingleBeanRegisterManager.register(registry, "kitExtensionAdviser", ExtensionAdviser.class);
        SingleBeanRegisterManager.register(registry, "kitBeanFactoryPostProcessor", KitBeanFactoryPostProcessor.class);
        SingleBeanRegisterManager.register(registry, "kitApplicationListener", KitApplicationListener.class);
        SingleBeanRegisterManager.register(registry, "kitSpringContext", KitSpringContext.class);
        BeanFactoryPostProcessPluginManager.addPlugin(registry, new ExtensionBeanFactoryPostProcessPlugin());
        ApplicationListenPluginManager.addPlugin(new ExtensionApplicationListenPlugin());
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
