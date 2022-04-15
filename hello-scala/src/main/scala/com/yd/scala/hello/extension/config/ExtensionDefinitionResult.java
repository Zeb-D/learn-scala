package com.yd.scala.hello.extension.config;

import java.io.Serializable;
import java.util.List;

/**
 * @author Zeb灬D
 * @date 2021/5/20 11:35 上午
 */
public class ExtensionDefinitionResult implements Serializable {


    private static final long serialVersionUID = 6482440981301084238L;
    private List<ExtensionDefinition> appendDefinitions;
    private List<ExtensionDefinition> deletedDefinitions;

    public List<ExtensionDefinition> getAppendDefinitions() {
        return appendDefinitions;
    }

    public void setAppendDefinitions(List<ExtensionDefinition> appendDefinitions) {
        this.appendDefinitions = appendDefinitions;
    }

    public List<ExtensionDefinition> getDeletedDefinitions() {
        return deletedDefinitions;
    }

    public void setDeletedDefinitions(List<ExtensionDefinition> deletedDefinitions) {
        this.deletedDefinitions = deletedDefinitions;
    }

}
