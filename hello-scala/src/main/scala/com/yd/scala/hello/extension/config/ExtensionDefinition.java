package com.yd.scala.hello.extension.config;

import java.io.Serializable;

/**
 * @author Zeb灬D
 * @date 2021/5/20 10:55 上午
 */

public class ExtensionDefinition implements Serializable {

    private static final long serialVersionUID = -6335852344929589601L;
    //全局唯一
    private String id;
    //分组名
    private String group;
    //分组内唯一
    private String extensionPointName;
    //before、replace、after
    private String action;
    //匹配规则
    private Script script;
    //扩展实现接口名
    private String extensionInterfaceName;
    //扩展实现方法名
    private String extensionMethodName;
    //顺序值，越小越靠前执行，支持负数
    private int order;
    //远程扩展实现的超时时间，本地实现会被忽略
    private int timeout;
    //远程扩展实现的重试次数，本地实现会被忽略
    private int retries;
    //懒加载
    private boolean lazyInit;
    //更新时间
    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getExtensionPointName() {
        return extensionPointName;
    }

    public void setExtensionPointName(String extensionPointName) {
        this.extensionPointName = extensionPointName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public String getExtensionInterfaceName() {
        return extensionInterfaceName;
    }

    public void setExtensionInterfaceName(String extensionInterfaceName) {
        this.extensionInterfaceName = extensionInterfaceName;
    }

    public String getExtensionMethodName() {
        return extensionMethodName;
    }

    public void setExtensionMethodName(String extensionMethodName) {
        this.extensionMethodName = extensionMethodName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
