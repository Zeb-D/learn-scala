package com.yd.scala.hello.extension.adapter.local;

import com.yd.scala.hello.extension.adapter.ExtensionAdapter;

import java.lang.reflect.Type;

/**
 * @author Zeb灬D
 * @date 2021/5/20 9:49 上午
 */

public interface LocalAdapterFactory {
    ExtensionAdapter getAdapter(String interfaceName, String methodName, Type returnType);
}
