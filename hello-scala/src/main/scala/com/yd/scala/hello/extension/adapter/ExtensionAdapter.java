package com.yd.scala.hello.extension.adapter;


import com.yd.scala.hello.extension.config.ExtensionParameter;
import com.yd.scala.hello.extension.config.ExtensionResult;

/**
 * @author Zeb灬D
 * @date 2021/5/22 12:34 下午
 */

public abstract class ExtensionAdapter {

    public void init() {

    }

    public void destroy() {

    }

    public abstract ExtensionResult<?> doExtend(ExtensionParameter param);
}
