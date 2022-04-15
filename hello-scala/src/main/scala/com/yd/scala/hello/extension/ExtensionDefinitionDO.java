package com.yd.scala.hello.extension;

import lombok.Data;


@Data
public class ExtensionDefinitionDO {
    private static final long serialVersionUID = 4705749775292492092L;
    private String group;
    private String extensionPointName;
    private String action;
    private String script;
    private String extensionInterfaceName;
    private String extensionMethodName;
    private Integer status;
    private long timeout;
    private int retries;
    private int env;
    private String description;
    private Integer order;
    private boolean lazyInit;

    private Long gmtCreate;
    private Long gmtModified;
    private Long id;
}
