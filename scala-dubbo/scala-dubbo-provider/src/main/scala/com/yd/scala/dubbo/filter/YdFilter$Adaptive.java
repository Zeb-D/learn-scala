package com.yd.scala.dubbo.filter;

import org.apache.dubbo.common.extension.ExtensionLoader;

public class YdFilter$Adaptive implements com.yd.scala.dubbo.filter.YdFilter {
    public org.apache.dubbo.rpc.Result invoke(org.apache.dubbo.rpc.Invoker arg0, org.apache.dubbo.rpc.Invocation arg1) throws org.apache.dubbo.rpc.RpcException {
        if (arg0 == null) throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument == null");
        if (arg0.getUrl() == null)
            throw new IllegalArgumentException("org.apache.dubbo.rpc.Invoker argument getUrl() == null");
        org.apache.dubbo.common.URL url = arg0.getUrl();
        if (arg1 == null) throw new IllegalArgumentException("invocation == null");
        String methodName = arg1.getMethodName();
        String extName = url.getMethodParameter(methodName, "yd.filter", "def");
        if (extName == null)
            throw new IllegalStateException("Failed to get extension (com.yd.scala.dubbo.filter.YdFilter) name from url (" + url.toString() + ") use keys([yd.filter])");
        com.yd.scala.dubbo.filter.YdFilter extension = (com.yd.scala.dubbo.filter.YdFilter) ExtensionLoader.getExtensionLoader(com.yd.scala.dubbo.filter.YdFilter.class).getExtension(extName);
        return extension.invoke(arg0, arg1);
    }
}