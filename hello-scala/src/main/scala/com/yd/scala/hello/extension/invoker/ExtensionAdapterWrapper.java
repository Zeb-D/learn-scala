package com.yd.scala.hello.extension.invoker;

import com.yd.scala.hello.extension.adapter.ConsoleAdapterFactory;
import com.yd.scala.hello.extension.adapter.ExtensionAdapter;
import com.yd.scala.hello.extension.config.*;
import com.yd.scala.hello.extension.exception.ExtensionStateException;

/**
 * @author Zeb灬D
 * @date 2021/5/20 9:12 下午
 */

public class ExtensionAdapterWrapper {
    private final ExtensionDefinition definition;
    private volatile ExtensionAdapter adapter;
    private volatile boolean initialized;
    private volatile boolean destroyed;

    public ExtensionAdapterWrapper(ExtensionDefinition definition) {
        this.definition = definition;
        if (!this.getDefinition().isLazyInit()) {
            initReference();
        }
    }

    public String getDefinitionId() {
        return definition.getId();
    }

    public String getExtensionPointName() {
        return definition.getExtensionPointName();
    }

    public String getAction() {
        return definition.getAction();
    }

    public String getName() {
        return definition.getExtensionInterfaceName() + "#" + definition.getExtensionMethodName();
    }

    public int getOrder() {
        return definition.getOrder();
    }

    public Script getScript() {
        return definition.getScript();
    }

    public ExtensionDefinition getDefinition() {
        return definition;
    }

    public ExtensionResult<?> doExtend(ExtensionParameter param) {
        if (destroyed) {
            throw new ExtensionStateException("The invoker(" + getName() + ") has already destroyed!");
        }
        if (!initialized) {
            initReference();
        }
        return adapter.doExtend(param);
    }

    private synchronized void initReference() {
        if (initialized) {
            return;
        }
        ExtensionPointDefinition pointDefinition = BaseConfig.getExtensionPointDefinition(definition.getExtensionPointName());

        adapter = ConsoleAdapterFactory.getAdapter(definition.getExtensionInterfaceName()
                , definition.getExtensionMethodName()
                , pointDefinition.getReturnType()
                , definition.getTimeout()
                , definition.getRetries());

        adapter.init();

        initialized = true;
    }

    public long getUpdateTime() {
        return definition.getUpdateTime();
    }

    public synchronized void destroy() {
        if (destroyed) {
            return;
        }
        if (adapter != null) {
            adapter.destroy();
            adapter = null;
        }
        destroyed = true;
    }
}
