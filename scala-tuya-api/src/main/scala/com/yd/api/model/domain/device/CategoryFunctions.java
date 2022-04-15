package com.yd.api.model.domain.device;

import java.util.List;

/**
 * 品类的function返回结果集
 *
 * @date: 2019-02-10 16:39
 */
public class CategoryFunctions {

    // 设备类别
    private String category;

    // function集
    private List<Function> functions;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}
