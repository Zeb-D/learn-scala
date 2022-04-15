package com.yd.api.model.domain.device;

/**
 *
 * @date: 2019-01-30 15:14
 */
public class Function {

    // 指令集名称
    private String code;
    // 指令集类型
    private String type;
    // 指令集入参范围
    private String values;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }
}
