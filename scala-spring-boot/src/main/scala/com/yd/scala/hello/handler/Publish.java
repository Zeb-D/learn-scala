package com.yd.scala.hello.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author created by Zeb灬D on 2021-03-09 16:12
 */
@Component
public class Publish {
    @Autowired
    private ApplicationContext applicationContext;

    public void publish(Event event) {
        applicationContext.publishEvent(event);
    }
}
