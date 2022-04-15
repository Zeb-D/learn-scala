package com.yd.scala.hello.extension.annotation;

import com.yd.scala.hello.extension.invoker.ExtensionPointInvokerPool;
import com.yd.scala.hello.extension.manager.ApplicationListenPlugin;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Zeb灬D
 * @date 2021/6/1 3:26 下午
 */

public class ExtensionApplicationListenPlugin implements ApplicationListenPlugin {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ExtensionPointInvokerPool.init();
    }
}
