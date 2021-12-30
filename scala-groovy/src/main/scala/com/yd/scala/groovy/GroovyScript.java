package com.yd.scala.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.codehaus.groovy.control.CompilationFailedException;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 运行groovy脚本的工具类
 *
 * @author Zeb灬D
 */
public class GroovyScript {

    /**
     * 脚本名称
     */
    private String name;

    private GroovyShell shell = new GroovyShell(new Binding());

    private Map<String, Script> scriptMap = new ConcurrentHashMap<>();
    private Script script;

    public GroovyScript(String name) {
        this.name = name;
    }

    public GroovyScript(String name, Map<String, Object> paramterMap) {
        this.name = name;
        setParams(paramterMap);
    }

    /**
     * 添加参数
     *
     * @param key
     * @param value
     */
    public void addParam(String key, Object value) {
        shell.setProperty(key, value);
    }

    /**
     * 设置参数
     *
     * @param paramterMap
     */
    public void setParams(Map<String, Object> paramterMap) {
        for (Map.Entry<String, Object> entry : paramterMap.entrySet()) {
            shell.setProperty(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 构建
     *
     * @param content
     * @param scriptType
     * @return
     * @throws CompilationFailedException
     * @throws IOException
     */
    public Script build(String content, ScriptType scriptType) throws CompilationFailedException, IOException {
        if (ScriptType.FILE == scriptType) {
            script = shell.parse(new File(content));

        } else if (scriptType == ScriptType.TEXT) {
            script = shell.parse(content);
        }
        return script;
    }

    /**
     * 执行脚本
     *
     * @return
     */
    public Object run() {
        if (script == null) {
            throw new RuntimeException("script need to be build");
        }
        return script.run();
    }

    public String getName() {
        return name;
    }
}
