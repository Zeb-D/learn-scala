package com.yd.scala.hello.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 这里主要引入下xml自定义方式
 *
 * @author created by Zeb灬D on 2021-07-01 15:44
 */
@Configuration
@ImportResource(locations = {"classpath:spring/conf.xml"})
public class YdaConfig {
}
