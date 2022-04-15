package com.yd.scala.hello.extension.adapter.remote;

import com.yd.scala.hello.extension.adapter.ExtensionAdapter;


public interface RemoteAdapterFactory {
    ExtensionAdapter getAdapter(String interfaceName, String methodName, int timeout, int retries);
}
