package com.yd.scala.hello.extension.manager;

import com.yd.scala.hello.extension.spring.KitApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zeb灬D
 * @date 2021/5/31 11:57 上午
 */

public class ApplicationListenPluginManager {
    private static final List<ApplicationListenPlugin> plugins = new LinkedList<>();

    public static void addPlugin(ApplicationListenPlugin plugin) {
        SingleBeanRegisterManager.register(null, "kitApplicationListener", KitApplicationListener.class);
        plugins.add(plugin);
    }

    public static void onApplicationEvent(ContextRefreshedEvent event) {
        for (ApplicationListenPlugin plugin : plugins) {
            plugin.onApplicationEvent(event);
        }
    }
}
