package com.yd.scala.hello.extension.spring;

import com.yd.scala.hello.extension.manager.ApplicationListenPluginManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Zeb灬D
 * @date 2021/5/31 12:04 下午
 */

public class KitApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static volatile boolean initialized;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initialized || event.getApplicationContext().getParent() != null) {
            return;
        }
        ApplicationListenPluginManager.onApplicationEvent(event);
        initialized = true;
    }
}
