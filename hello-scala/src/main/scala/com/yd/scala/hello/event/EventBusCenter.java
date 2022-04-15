package com.yd.scala.hello.event;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Component
public class EventBusCenter implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(EventBusCenter.class);
    private static ApplicationContext applicationContext;

    private EventBus eventBus;

    private AsyncEventBus asyncEventBus;

    public EventBusCenter() {
        initEventBus();
        initAsyncEventBus(Executors.newFixedThreadPool(4));
    }

    public void postSync(AbstractEvent abstractEvent) {
        eventBus.post(abstractEvent);
    }

    public void postAsync(AbstractEvent abstractEvent) {
        asyncEventBus.post(abstractEvent);
    }

    private void initEventBus() {
        eventBus = new EventBus((t, ctx) -> {
            //todo 事件异常处理
            logger.error(message(ctx), t);
        });
    }

    private void initAsyncEventBus(Executor asyncEventExecutor) {
        asyncEventBus = new AsyncEventBus(asyncEventExecutor, (t, ctx) -> {
            //todo 事件异常处理，是否需要补偿措施
            logger.error(message(ctx), t);
        });
    }

    private static String message(SubscriberExceptionContext context) {
        Method method = context.getSubscriberMethod();
        return "Exception thrown by subscriber method "
                + method.getName()
                + '('
                + method.getParameterTypes()[0].getName()
                + ')'
                + " on subscriber "
                + context.getSubscriber()
                + " when dispatching event: "
                + context.getEvent();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Collection<Object> registers = applicationContext.getBeansWithAnnotation(EventBusListener.class).values();
        logger.info("EventBus注册列表：{}", registers);
        if (CollectionUtils.isEmpty(registers)) {
            return;
        }
        registers.stream().forEach(listener -> {
            eventBus.register(listener);
            asyncEventBus.register(listener);
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext a) throws BeansException {
        applicationContext = a;
    }
}
