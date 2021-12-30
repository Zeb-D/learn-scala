package com.yd.scala.grpc.sigmax;

import com.tuya.grpc.client.aspect.GrpcCallAspect;
import com.tuya.grpc.client.consumer.AbstractConsumer;
import com.tuya.grpc.client.consumer.ConsumerConfig;
import com.tuya.grpc.client.consumer.ConsumerServer;
import com.tuya.grpc.client.register.etcd.EtcdRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class SigmaConfig {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    EtcdRegister sigmaRegister() {
        if (!isPermitUse()) {
            return null;
        }
        String etcdUrl = "10.0.200.124:12379";
        return new EtcdRegister("grpc", etcdUrl);
    }

    @Bean
    ConsumerConfig consumerConfig() {
        if (!isPermitUse()) {
            return null;
        }
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setRegister(sigmaRegister());
        return consumerConfig;
    }

    @Bean
    SigmaxConsumer sigmaxConsumer() {
        SigmaxConsumer sigmaxConsumer = new SigmaxConsumer("s");
        return new SigmaxConsumer("s");
    }

    @Bean
    ConsumerServer consumerServer() {
        if (!isPermitUse()) {
            return null;
        }
        List<AbstractConsumer> consumers = new ArrayList<>();
        consumers.add(sigmaxConsumer());
        return new ConsumerServer(consumers);
    }

    @Bean
    GrpcCallAspect grpcCallAspect() {
        return new GrpcCallAspect();
    }

    private boolean isPermitUse() {
        logger.info("permit use");
        return true;
    }
}
