package com.yd.scala.hello.handler;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author created by Zeb灬D on 2021-03-09 16:12
 */
@Component
public class Listener {

    @EventListener
    public void listenEvent(Event event) {
        System.out.println("\n--event 收到；" + event);
        // do some thing
    }
}
