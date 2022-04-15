package com.yd.scala.hello.extension.config;

import org.apache.dubbo.config.ReferenceConfig;

import java.util.List;

/**
 * @author Zeb灬D
 * @date 2021/5/25 5:39 下午
 */

public class DefaultExtensionDefinitionFactory implements ExtensionDefinitionFactory {

    private volatile ReferenceConfig<ExtensionDefinitionService> referenceConfig;
    private volatile ExtensionDefinitionService extensionDefinitionService;

    @Override
    public ExtensionDefinitionResult getExtensionDefinitions(String group, List<ExtensionDefinition> definitions) {
        if (referenceConfig == null) {
            init();
        }
        return extensionDefinitionService.getExtensionDefinitions(group, definitions);
    }

    private synchronized void init() {
        if (referenceConfig != null) {
            return;
        }
        referenceConfig = new ReferenceConfig<>();
        referenceConfig.setRetries(5);
        referenceConfig.setTimeout(30000);
        referenceConfig.setInterface(ExtensionDefinitionService.class);
        extensionDefinitionService = referenceConfig.get();
    }
}
