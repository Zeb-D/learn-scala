package com.yd.scala.dubbo.provider;

import com.yd.scala.dubbo.client.IHelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.concurrent.CountDownLatch;

/**
 * @author created by Zeb灬D on 2021-07-31 18:03
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ServiceConfig<IHelloService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig(
                "zookeeper://" + "localhost" + ":" + "2181"));
        service.setInterface(IHelloService.class);
        service.setRef(new HelloService());
        service.export();

        System.out.println("dubbo service started");
        new CountDownLatch(1).await();
    }
}
