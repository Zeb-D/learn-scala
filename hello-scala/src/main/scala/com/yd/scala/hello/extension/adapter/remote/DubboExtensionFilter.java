package com.yd.scala.hello.extension.adapter.remote;

import com.yd.scala.hello.extension.config.ExtensionParameter;
import org.apache.dubbo.rpc.*;



public class DubboExtensionFilter implements Filter {

    private final Class<?>[] parameterTypes = new Class[]{ExtensionParameter.class};
    private final String parameterTypesDesc = "Lcom/yd/scala/hello/extension/config/ExtensionParameter;";
    private final String[] compatibleParamSignatures = new String[]{ExtensionParameter.class.getName()};

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcInvocation rpcInvocation = (RpcInvocation) invocation;
        Object[] arguments = rpcInvocation.getArguments();
        rpcInvocation.setMethodName((String) arguments[0]);
        rpcInvocation.setParameterTypes(parameterTypes);
        rpcInvocation.setParameterTypesDesc(parameterTypesDesc);
        rpcInvocation.setCompatibleParamSignatures(compatibleParamSignatures);
        rpcInvocation.setArguments(new Object[]{arguments[1]});

        return invoker.invoke(rpcInvocation);
    }
}
