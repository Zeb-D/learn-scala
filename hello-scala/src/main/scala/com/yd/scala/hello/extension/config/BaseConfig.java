package com.yd.scala.hello.extension.config;

import com.yd.scala.hello.extension.adapter.local.LocalAdapterFactory;
import com.yd.scala.hello.extension.adapter.local.SpringLocalAdapterFactory;
import com.yd.scala.hello.extension.adapter.remote.DubboRemoteAdapterFactory;
import com.yd.scala.hello.extension.adapter.remote.RemoteAdapterFactory;
import com.yd.scala.hello.extension.exception.ExtensionStateException;
import com.yd.scala.hello.extension.spring.KitSpringContext;
import org.apache.dubbo.common.utils.StringUtils;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zeb灬D
 * @date 2021/5/21 3:40 下午
 */

public class BaseConfig {

    private volatile static String appName;

    private volatile static int referenceTimeoutMills = 1000;

    private volatile static int referenceRetries = 3;

    private volatile static long definitionRefreshMills = 600000L;

    private static Object extensionDefinitionFactoryNameOrClass;

    private volatile static ExtensionDefinitionFactory extensionDefinitionFactory;

    private volatile static LocalAdapterFactory localAdapterFactory = new SpringLocalAdapterFactory();

    private volatile static RemoteAdapterFactory remoteAdapterFactory = new DubboRemoteAdapterFactory();

    private static final Map<String, ExtensionPointDefinition> extensionPointDefinitionMap = new ConcurrentHashMap<>();

    public static String getAppName() {
        return appName;
    }

    public static void setAppName(String appName) {
        BaseConfig.appName = appName;
    }

    public static int getReferenceTimeoutMills() {
        return referenceTimeoutMills;
    }

    public static void setReferenceTimeoutMills(int referenceTimeoutMills) {
        BaseConfig.referenceTimeoutMills = referenceTimeoutMills;
    }

    public static long getDefinitionRefreshMills() {
        return definitionRefreshMills;
    }

    public static void setDefinitionRefreshMills(long definitionRefreshMills) {
        BaseConfig.definitionRefreshMills = definitionRefreshMills;
    }

    public static int getReferenceRetries() {
        return referenceRetries;
    }

    public static void setReferenceRetries(int referenceRetries) {
        BaseConfig.referenceRetries = referenceRetries;
    }

    public static ExtensionDefinitionFactory getExtensionDefinitionFactory() {
        if (extensionDefinitionFactory != null) {
            return extensionDefinitionFactory;
        }

        synchronized (ExtensionDefinitionFactory.class) {
            if (extensionDefinitionFactoryNameOrClass == null) {
                throw new IllegalStateException("No factory is configured");
            }
            if (extensionDefinitionFactoryNameOrClass instanceof Class) {
                Class<?> factoryClass = (Class<?>) extensionDefinitionFactoryNameOrClass;
                if (!ExtensionDefinitionFactory.class.isAssignableFrom(factoryClass)) {
                    throw new IllegalStateException("Invalid class");
                }
                try {
                    extensionDefinitionFactory = (ExtensionDefinitionFactory) KitSpringContext.getApplicationContext().getBean(factoryClass);
                } catch (Exception ignored) {

                }
                if (extensionDefinitionFactory == null) {
                    try {
                        Constructor<?> constructor = factoryClass.getDeclaredConstructor((Class<?>[]) null);
                        extensionDefinitionFactory = (ExtensionDefinitionFactory) constructor.newInstance();
                    } catch (Exception e) {
                        throw new IllegalStateException("Invalid class", e);
                    }
                }
            } else {
                String factoryBeanName = (String) extensionDefinitionFactoryNameOrClass;
                extensionDefinitionFactory = (ExtensionDefinitionFactory) KitSpringContext.getApplicationContext().getBean(factoryBeanName);
            }
        }
        return extensionDefinitionFactory;
    }

    public static void setExtensionDefinitionFactory(ExtensionDefinitionFactory extensionDefinitionFactory) {
        BaseConfig.extensionDefinitionFactory = extensionDefinitionFactory;
    }

    public static void setExtensionDefinitionFactoryName(String name) {
        extensionDefinitionFactoryNameOrClass = name;
    }

    public static void setExtensionDefinitionFactoryClass(Class<?> clazz) {
        extensionDefinitionFactoryNameOrClass = clazz;
    }

    public static LocalAdapterFactory getLocalAdapterFactory() {
        return localAdapterFactory;
    }

    public static void setLocalAdapterFactory(LocalAdapterFactory localAdapterFactory) {
        BaseConfig.localAdapterFactory = localAdapterFactory;
    }

    public static RemoteAdapterFactory getRemoteAdapterFactory() {
        return remoteAdapterFactory;
    }

    public static void setRemoteAdapterFactory(RemoteAdapterFactory remoteAdapterFactory) {
        BaseConfig.remoteAdapterFactory = remoteAdapterFactory;
    }

    public static void addExtensionPointDefinition(ExtensionPointDefinition definition) {
        String name = definition.getExtensionPointName();
        if (StringUtils.isBlank(name)) {
            throw new ExtensionStateException("invalid name");
        }
        if (extensionPointDefinitionMap.containsKey(name)) {
            throw new ExtensionStateException("duplicate name '" + name + "'");
        }
        extensionPointDefinitionMap.put(definition.getExtensionPointName(), definition);
    }

    public static ExtensionPointDefinition getExtensionPointDefinition(String name) {
        return extensionPointDefinitionMap.get(name);
    }

    public static List<ExtensionPointDefinition> getAllExtensionPointDefinitions() {
        return new LinkedList<>(extensionPointDefinitionMap.values());
    }
}
