//package com.yd.scala.dubbo.config;
//
//import com.yd.scala.dubbo.client.IHelloService;
//import org.apache.dubbo.config.ServiceConfig;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author created by Zeb灬D on 2020-06-01 23:36
// */
//@Configuration
//public class ProviderConfig {
//
//    @Bean("helloService1")
//    public ServiceConfig helloService(IHelloService helloService){
//        ServiceConfig config = new ServiceConfig();
//        config.setInterface(IHelloService.class.getName());
//        config.setRef(helloService);
//        return config;
//    }
//
//}
