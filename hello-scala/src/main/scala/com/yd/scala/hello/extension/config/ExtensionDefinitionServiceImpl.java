package com.yd.scala.hello.extension.config;

import com.yd.json.JSON;
import com.yd.scala.hello.extension.ExtensionDefinitionDO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (`id`,`group`,`extension_point_name`,`action`,`extension_interface_name`,`extension_method_name`,`timeout`,`retries`,`status`,`env`,`gmt_create`,`gmt_modified`,`description`,`order`,`script`,`lazy_init`) VALUES
 * (1,"yd","demo","before","com.yd.scala.hello.service.extension.BeforeExtensionDemoService","before",0,0,1,0,20210526105124,20210526105124,"测试前置",0,"",0);
 * (2,"yd","demo","replace","com.yd.scala.hello.service.extension.BeforeExtensionDemoService","replace",0,0,1,0,20210526105124,20210526105124,"测试替换",0,"",0);
 * (3,"yd","demo","after","com.yd.scala.hello.service.extension.BeforeExtensionDemoService","after",0,0,1,0,20210526105124,20210526105124,"测试后置",0,"",0);
 * (4,"yd","demo","before","com.yd.scala.hello.service.extension.BeforeExtensionDemoService","before",0,0,1,1,20210526105124,20210526105124,"测试前置",2,"{\"type\":\"object_path_value\",\"script\":\"$[0]\",\"operator\":\"=\",\"values\":[1]}",0);
 * (5,"yd","demo","replace","com.yd.scala.hello.service.extension.ReplaceExtensionDemoService","replace",0,0,1,1,20210526105124,20210526105124,"测试替换",0,"{\"type\":\"object_path_value\",\"script\":\"$[0]\",\"operator\":\"=\",\"values\":[1]}",0);
 * (6,"yd","demo","after","com.yd.scala.hello.service.extension.AfterExtensionDemoService","after",0,0,1,1,20210526105124,20210526105124,"测试后置",0,"{\"type\":\"object_path_value\",\"script\":\"$[0]\",\"operator\":\"=\",\"values\":[1]}",0);
 * (7,"yd","demo","before","com.yd.scala.hello.service.extension.ExtensionDubboService","before",0,0,1,1,20210526105124,20210526105124,"dubbo测试前置",1,"{\"type\":\"object_path_value\",\"script\":\"$[0]\",\"operator\":\"=\",\"values\":[1]}",0);
 * (8,"yd","demo","replace","com.yd.scala.hello.service.extension.ExtensionDubboService","replace",0,0,1,1,20210526105124,20210526105124,"dubbo测试替换",0,"{\"type\":\"object_path_value\",\"script\":\"$[0]\",\"operator\":\"=\",\"values\":[1]}",0);
 * (9,"yd","demo","after","com.yd.scala.hello.service.extension.ExtensionDubboService","after",0,0,1,1,20210526105124,20210526105124,"dubbo测试后置",0,"{\"type\":\"object_path_value\",\"script\":\"$[0]\",\"operator\":\"=\",\"values\":[1]}",0);
 *
 * @since
 */
@Service("extensionDefinitionService")
public class ExtensionDefinitionServiceImpl implements ExtensionDefinitionService {

    private List<ExtensionDefinitionDO> getExtensionDefinitions(String group) {
        List<ExtensionDefinitionDO> definitionDOList = new LinkedList<>();
        ExtensionDefinitionDO definitionDO = new ExtensionDefinitionDO();
        definitionDO.setId(1L);
        definitionDO.setGroup("yd");
        definitionDO.setExtensionPointName("demo");
        definitionDO.setAction("before");
        definitionDO.setExtensionInterfaceName("com.yd.scala.hello.service.extension.BeforeExtensionDemoService");
        definitionDO.setExtensionMethodName("before");
        definitionDO.setTimeout(5);
        definitionDO.setStatus(1);
        definitionDO.setEnv(1);
        definitionDO.setGmtCreate(System.currentTimeMillis());
        definitionDO.setGmtModified(System.currentTimeMillis());
        definitionDOList.add(definitionDO);
        return definitionDOList;
    }

    @Override
    public ExtensionDefinitionResult getExtensionDefinitions(String group, List<ExtensionDefinition> definitions) {
        List<ExtensionDefinitionDO> definitionDOList =
                getExtensionDefinitions(group).stream().collect(Collectors.toList());
        Map<String, Long> currentDefinitions = definitions.stream()
                .collect(Collectors.toMap(ExtensionDefinition::getId, ExtensionDefinition::getUpdateTime, (o1, o2) -> o1));

        List<ExtensionDefinition> append = new ArrayList<>();
        List<ExtensionDefinition> remove = new ArrayList<>();

        for (ExtensionDefinitionDO definitionDO : definitionDOList) {
            Long updateTime = currentDefinitions.get(definitionDO.getId());
            if (ObjectUtils.isEmpty(updateTime) && definitionDO.getStatus() > 0) {
                append.add(convertExtensionDefinition(definitionDO));
                continue;
            }
            if (!ObjectUtils.isEmpty(updateTime) && definitionDO.getGmtModified() > updateTime && definitionDO.getStatus() == 0) {
                remove.add(convertExtensionDefinition(definitionDO));
            }
        }

        ExtensionDefinitionResult result = new ExtensionDefinitionResult();
        result.setDeletedDefinitions(remove);
        result.setAppendDefinitions(append);

        return result;
    }

    private ExtensionDefinition convertExtensionDefinition(ExtensionDefinitionDO extensionDefinitionDO) {
        ExtensionDefinition extensionDefinition = new ExtensionDefinition();
        extensionDefinition.setId(extensionDefinitionDO.getId().toString());
        extensionDefinition.setExtensionPointName(extensionDefinitionDO.getExtensionPointName());
        extensionDefinition.setScript(JSON.parseObject(extensionDefinitionDO.getScript(), Script.class));
        extensionDefinition.setAction(extensionDefinitionDO.getAction());
        extensionDefinition.setExtensionInterfaceName(extensionDefinitionDO.getExtensionInterfaceName());
        extensionDefinition.setExtensionMethodName(extensionDefinitionDO.getExtensionMethodName());
        extensionDefinition.setTimeout((int) extensionDefinitionDO.getTimeout());
        extensionDefinition.setRetries(extensionDefinitionDO.getRetries());
        extensionDefinition.setUpdateTime(extensionDefinitionDO.getGmtModified());
        extensionDefinition.setOrder(extensionDefinitionDO.getOrder());
        return extensionDefinition;
    }
}
