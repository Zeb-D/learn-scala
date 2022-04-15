package com.yd.scala.hello.extension.manager;

import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Zeb灬D
 * @date 2021/5/31 11:56 上午
 */

public interface ApplicationListenPlugin {
     void onApplicationEvent(ContextRefreshedEvent event);
}
