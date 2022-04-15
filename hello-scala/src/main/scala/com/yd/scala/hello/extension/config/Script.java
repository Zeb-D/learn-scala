package com.yd.scala.hello.extension.config;

import java.io.Serializable;

/**
 * @author Zeb灬D
 * @date 2021/5/20 5:58 下午
 */

public class Script implements Serializable {


    private static final long serialVersionUID = 931035801633377871L;
    /**
     * 固定为 object_path_value
     */
    private String type;
    /**
     * 原方法取值表达式
     * $[0].user.name
     */
    private String script;
    /**
     * 关系运算符
     * > < =等
     */
    private String operator;
    /**
     * 需要匹配的值
     */
    private Object[] values;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
