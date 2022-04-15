package com.yd.scala.hello.handler.spring;

import com.yd.scala.hello.handler.protocol.Protocol;
import com.yd.scala.hello.handler.protocol.Protocols;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ProtocolDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        String id = element.getAttribute("id");
        if (StringUtils.isBlank(id)) {
            return null;
        }
        beanDefinition.setLazyInit(true);
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        String clazzName = element.getAttribute("class");
        if (StringUtils.isNotBlank(clazzName)) {
            try {
                Class<?> clazz = Class.forName(clazzName);
                if (!Protocol.class.isAssignableFrom(clazz)) {
                    throw new IllegalStateException("Class " + clazzName + "必须实现Protocol接口");
                }
                beanDefinition.setBeanClassName(clazzName);
                beanDefinition.setBeanClass(clazz);
                Protocol protocol = (Protocol) clazz.newInstance();
                Protocols.put(id, protocol);
                if ("true".equalsIgnoreCase(element.getAttribute("default"))) {
                    Protocols.setDefaultProtocol(protocol);
                }
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class " + clazzName + " 不存在");
            } catch (IllegalAccessException | InstantiationException e) {
                throw new IllegalStateException("实例化 Class " + clazzName + " 失败", e);
            }
        }
        return beanDefinition;
    }
}
