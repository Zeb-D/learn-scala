package com.yd.scala.dubbo.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * application.dubbo.server.name=dubbo-server
 * application.dubbo.registry.address=zookeeper://10.0.200.5:2181
 * application.dubbo.server.client=zkclient
 * application.dubbo.server.protocolName=dubbo
 * application.dubbo.server.protocolPort=20880
 *
 * @author created by Zeb-D on 2019-04-30 11:34
 */
@Configuration
public class DubboConfig {

    @Value("${dubbo.server.name:dubboConsumer}")
    private String applicationName;
    @Value("${dubbo.registry.address:localhost:2181}")
    private String registryAddress;
    @Value("${dubbo.server.protocol:zookeeper}")
    private String registryProtocol;
    @Value("${dubbo.server.protocolName:dubbo}")
    private String protocolName;
    @Value("${dubbo.server.protocolPort:20880}")
    private Integer protocolPort;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(registryAddress);
        registryConfig.setProtocol(registryProtocol);
        registryConfig.setParameters(new HashMap<>());
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }

}
