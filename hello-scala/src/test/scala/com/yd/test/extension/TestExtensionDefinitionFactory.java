package com.yd.test.extension;

import com.yd.scala.hello.extension.config.ExtensionDefinition;
import com.yd.scala.hello.extension.config.ExtensionDefinitionFactory;
import com.yd.scala.hello.extension.config.ExtensionDefinitionResult;
import com.yd.scala.hello.extension.config.Script;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zeb灬D
 * @date 2021/5/26 7:40 下午
 */

public class TestExtensionDefinitionFactory implements ExtensionDefinitionFactory {


    @Override
    public ExtensionDefinitionResult getExtensionDefinitions(String group, List<ExtensionDefinition> definitions) {
        ExtensionDefinitionResult result = new ExtensionDefinitionResult();
        result.setDeletedDefinitions(definitions);

        ExtensionDefinition definition = new ExtensionDefinition();
        definition.setId("1111");
        definition.setExtensionPointName("demo_test");
        definition.setExtensionInterfaceName(ExtensionDemoService.class.getName());
        definition.setExtensionMethodName("test");
        definition.setUpdateTime(100000);
        definition.setAction("replace");
        definition.setRetries(3);
        definition.setTimeout(1000);

        Script script = new Script();
        script.setType("object_path_value");
        script.setScript("$[0]");
        script.setOperator("=");
        script.setValues(new Object[]{"1"});//第一个参数等于1才触发 代理处理

        definition.setScript(script);

        List<ExtensionDefinition> appendDefinitions = new ArrayList<>();
        appendDefinitions.add(definition);
        result.setAppendDefinitions(appendDefinitions);

        return result;
    }
}
