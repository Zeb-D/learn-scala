package com.yd.scala.hello.handler.spring;

import com.yd.scala.hello.handler.cluster.ClusterConfig;
import com.yd.scala.hello.handler.cluster.Clusters;
import com.yd.scala.hello.handler.path.PathConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class ClusterDefinitionParser implements BeanDefinitionParser {

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setLazyInit(true);

        String clusterId = element.getAttribute("id");
        if (StringUtils.isBlank(clusterId)) {
            return null;
        }
        beanDefinition.setLazyInit(true);
        parserContext.getRegistry().registerBeanDefinition(clusterId, beanDefinition);
        beanDefinition.setBeanClass(ClusterConfig.class);
        beanDefinition.getPropertyValues().addPropertyValue("id", clusterId);
        NodeList nodeList = element.getChildNodes();
        List<PathConfig> pathConfigs = new ArrayList<>(nodeList.getLength());
        for (int i = 0; i < nodeList.getLength(); ++i) {
            Node node = nodeList.item(i);
            if (!(node instanceof Element)) {
                continue;
            }
            //if("cluster".equals(node.getLocalName()))
            Element elementNode = (Element) node;
            String path = elementNode.getAttribute("path");
            if (StringUtils.isBlank(path) || !path.startsWith("$.")) {
                throw new IllegalStateException("path配置错误");
            }
            String protocol = elementNode.getAttribute("protocol");
            PathConfig pathConfig = new PathConfig();
            pathConfig.setPath(path);
            pathConfig.setProtocol(protocol);

            pathConfigs.add(pathConfig);
        }
        if (pathConfigs.isEmpty()) {
            return null;
        }

        String protocol = element.getAttribute("protocol");
        beanDefinition.getPropertyValues().addPropertyValue("protocol", new RuntimeBeanNameReference(protocol));
        beanDefinition.getPropertyValues().addPropertyValue("pathConfigs", pathConfigs);

        ClusterConfig clusterConfig = new ClusterConfig();
        clusterConfig.setId(clusterId);
        clusterConfig.setPathConfigs(pathConfigs);
        clusterConfig.setProtocol(protocol);
        Clusters.put(clusterId, clusterConfig);

        return beanDefinition;
    }
}
