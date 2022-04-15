package com.yd.scala.hello.extension.config;

import java.util.List;

/**
 * @author Zeb灬D
 * @date 2021/5/20 10:43 上午
 */

public interface ExtensionDefinitionFactory {
    ExtensionDefinitionResult getExtensionDefinitions(String group, List<ExtensionDefinition> definitions);
}
