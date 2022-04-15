package com.yd.scala.hello.extension.config;

import java.util.List;

/**
 * @author Zeb灬D
 * @date 2021/5/25 7:08 下午
 */

public interface ExtensionDefinitionService {
    ExtensionDefinitionResult getExtensionDefinitions(String group, List<ExtensionDefinition> definitions);
}
