package com.yd.scala.hello.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

@Configuration
public class PropertiesFactoryBean implements FactoryBean<Properties>, EnvironmentAware {
    private final Properties properties = new Properties();

    {
        properties.put("name", "test");
    }

    private volatile boolean isInit = false;
    private Object lock = new Object();
    private String appName;
    private Environment environment;

    public Class<?> getObjectType() {
        return Properties.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public Properties getObject() throws Exception {
        if (!this.isInit) {
            synchronized (this.lock) {
                if (!this.isInit) {

                    if (this.environment instanceof ConfigurableEnvironment) {
                        ((ConfigurableEnvironment) this.environment).getPropertySources().addFirst(new PropertiesPropertySource(this.appName, this.properties));
                    }

                    this.isInit = true;
                }
            }
        }

        return this.properties;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}