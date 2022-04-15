package com.yd.scala.hello.handler.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


public class YdaNamespaceHandlerSupport extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("protocol", new ProtocolDefinitionParser());
        registerBeanDefinitionParser("cluster", new ClusterDefinitionParser());
    }
}
