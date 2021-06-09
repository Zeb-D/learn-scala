package com.yd.scala.hello.config;

import com.yd.scala.hello.hbase.service.HBaseClient;
import com.yd.scala.hello.hbase.service.HBaseClientImpl;
import com.yd.scala.hello.hbase.service.HBaseService;
import com.yd.scala.hello.hbase.service.IHBaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * docker run -d -h docker-hbase \
 *         -p 9181:2181 \
 *         -p 8080:8080 \
 *         -p 8085:8085 \
 *         -p 9090:9090 \
 *         -p 9000:9000 \
 *         -p 9095:9095 \
 *         -p 16000:16000 \
 *         -p 16010:16010 \
 *         -p 16201:16201 \
 *         -p 16301:16301 \
 *         -p 16020:16020\
 *         --name yd_hbasee \
 *         harisekhon/hbase:1.3
 *
 *         127.0.0.1 docker-hbase
 * @author created by Zeb灬D on 2021-02-26 23:08
 */
@Configuration
public class HBaseConfig {
    private String zkHost = "127.0.0.1:9181";

    @Bean
    public HBaseClient hBaseClient() {
        return new HBaseClientImpl(zkHost);
    }

    @Bean
    public IHBaseService hBaseService(){
        return new HBaseService();
    }

}
