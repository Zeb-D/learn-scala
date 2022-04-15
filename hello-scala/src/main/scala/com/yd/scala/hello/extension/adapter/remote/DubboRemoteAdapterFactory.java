package com.yd.scala.hello.extension.adapter.remote;

import com.yd.scala.hello.extension.adapter.ExtensionAdapter;


public class DubboRemoteAdapterFactory implements RemoteAdapterFactory {

    @Override
    public ExtensionAdapter getAdapter(String interfaceName, String methodName, int timeout, int retries) {
        DubboExtensionAdapter adapter = new DubboExtensionAdapter();
        adapter.setInterfaceName(interfaceName);
        adapter.setMethodName(methodName);
        adapter.setRetries(retries);
        adapter.setTimeout(timeout);
        return adapter;
    }
}
