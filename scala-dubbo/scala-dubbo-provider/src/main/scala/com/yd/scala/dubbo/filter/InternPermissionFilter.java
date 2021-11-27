package com.yd.scala.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.Random;

//@Adaptive//加上去了，就不会自适应类就是这个，就不会通过字节码去生成
@Activate(group = {"group1", "group2"}, order = 1)//打在某个自定义的Filter上
public class InternPermissionFilter implements YdFilter {
    private static final String errorCode = "1101";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        if (new Random(12).nextBoolean()) {
            return AsyncRpcResult.newDefaultAsyncResult(errorCode, invocation);
        }
        System.out.println(invoker.getInterface() + "." + invocation.getMethodName());
        return invoker.invoke(invocation);
    }
}
