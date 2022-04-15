package com.yd.scala.hello.extension.adapter;

import com.yd.scala.hello.extension.adapter.local.LocalAdapterFactory;
import com.yd.scala.hello.extension.adapter.local.SpringLocalAdapterFactory;
import com.yd.scala.hello.extension.adapter.remote.DubboRemoteAdapterFactory;
import com.yd.scala.hello.extension.adapter.remote.RemoteAdapterFactory;

import java.lang.reflect.Type;


public class ConsoleAdapterFactory {

    private static volatile LocalAdapterFactory localAdapterFactory = new SpringLocalAdapterFactory();

    private static volatile RemoteAdapterFactory remoteAdapterFactory = new DubboRemoteAdapterFactory();

    private ConsoleAdapterFactory() {

    }

    public static LocalAdapterFactory getLocalAdapterFactory() {
        return localAdapterFactory;
    }

    public static void setLocalAdapterFactory(LocalAdapterFactory localAdapterFactory) {
        ConsoleAdapterFactory.localAdapterFactory = localAdapterFactory;
    }

    public static RemoteAdapterFactory getRemoteAdapterFactory() {
        return remoteAdapterFactory;
    }

    public static void setRemoteAdapterFactory(RemoteAdapterFactory remoteAdapterFactory) {
        ConsoleAdapterFactory.remoteAdapterFactory = remoteAdapterFactory;
    }

    public static ExtensionAdapter getAdapter(String interfaceName, String methodName, Type returnType, int timeout, int retries) {
        ExtensionAdapter adapter = null;

        try {
            adapter = localAdapterFactory.getAdapter(interfaceName, methodName, returnType);
        } catch (Exception ignored) {

        }

        if (adapter == null) {
            adapter = remoteAdapterFactory.getAdapter(interfaceName, methodName, timeout, retries);
        }

        return adapter;
    }
}
