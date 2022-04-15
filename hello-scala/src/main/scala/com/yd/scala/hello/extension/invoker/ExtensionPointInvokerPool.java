package com.yd.scala.hello.extension.invoker;

import com.yd.json.JSON;
import com.yd.scala.hello.extension.config.BaseConfig;
import com.yd.scala.hello.extension.config.ExtensionDefinition;
import com.yd.scala.hello.extension.config.ExtensionDefinitionFactory;
import com.yd.scala.hello.extension.config.ExtensionDefinitionResult;
import com.yd.scala.hello.extension.manager.ScheduleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Zeb灬D
 * @date 2021/5/20 5:28 下午
 */
public class ExtensionPointInvokerPool {
    private static final Logger logger = LoggerFactory.getLogger(ExtensionPointInvokerPool.class);
    private static final ConcurrentHashMap<String, ExtensionPointInvoker> extensionPointInvokerMap = new ConcurrentHashMap<>();
    private static volatile boolean initialized;

    public static ExtensionPointInvoker getExtensionPointInvoker(String extensionPointName) {
        if (!initialized) {
            init();
        }
        return extensionPointInvokerMap.get(extensionPointName);
    }

    public synchronized static void refresh() {
        ExtensionDefinitionFactory definitionFactory = BaseConfig.getExtensionDefinitionFactory();
        List<ExtensionDefinition> curDefinitions = new LinkedList<>();
        for (ExtensionPointInvoker invoker : extensionPointInvokerMap.values()) {
            List<ExtensionDefinition> definitions = invoker.getDefinitions();
            for (ExtensionDefinition definition : definitions) {
                ExtensionDefinition simpleDefinition = new ExtensionDefinition();
                simpleDefinition.setId(definition.getId());
                simpleDefinition.setUpdateTime(definition.getUpdateTime());
                curDefinitions.add(simpleDefinition);
            }
        }
        ExtensionDefinitionResult extensionDefinitionResult = definitionFactory.getExtensionDefinitions(BaseConfig.getAppName(), curDefinitions);
        logger.info("load extension config config:{}", JSON.toJSONString(extensionDefinitionResult));
        List<ExtensionAdapterWrapper> needDestroyWrappers = new LinkedList<>();
        Set<ExtensionPointInvoker> needRefreshInvokers = new HashSet<>();
        for (ExtensionDefinition definition : extensionDefinitionResult.getAppendDefinitions()) {
            ExtensionPointInvoker invoker = extensionPointInvokerMap.get(definition.getExtensionPointName());
            if (invoker == null) {
                invoker = new ExtensionPointInvoker();
                extensionPointInvokerMap.put(definition.getExtensionPointName(), invoker);
            }
            needRefreshInvokers.add(invoker);
            ExtensionAdapterWrapper previousWrapper = invoker.addExtension(definition);
            if (previousWrapper != null) {
                needDestroyWrappers.add(previousWrapper);
            }
        }

        for (ExtensionDefinition definition : extensionDefinitionResult.getDeletedDefinitions()) {
            ExtensionPointInvoker invoker = extensionPointInvokerMap.get(definition.getExtensionPointName());
            if (invoker == null) {
                continue;
            }
            ExtensionAdapterWrapper previousWrapper = invoker.removeExtension(definition.getId());
            if (previousWrapper != null) {
                needDestroyWrappers.add(previousWrapper);
            }
        }

        for (ExtensionPointInvoker invoker : needRefreshInvokers) {
            invoker.refresh();
        }

        ScheduleManager.addDelayTask(new DelayDestroy(needDestroyWrappers), 5, TimeUnit.MINUTES);
    }

    public static class DelayDestroy implements Runnable {
        private final List<ExtensionAdapterWrapper> needDestroyWrappers = new LinkedList<>();

        public DelayDestroy(List<ExtensionAdapterWrapper> needDestroyWrappers) {
            this.needDestroyWrappers.addAll(needDestroyWrappers);
        }

        @Override
        public void run() {
            for (ExtensionAdapterWrapper wrapper : needDestroyWrappers) {
                wrapper.destroy();
            }
        }
    }

    public static synchronized void init() {
        if (initialized) {
            return;
        }
        refresh();
        initialized = true;
        ScheduleManager.addScheduledTask(ExtensionPointInvokerPool::refresh
                , BaseConfig.getDefinitionRefreshMills(), BaseConfig.getDefinitionRefreshMills(), TimeUnit.MILLISECONDS);
    }
}
