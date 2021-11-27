package com.yd.scala.dubbo.filter;

import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author created by Zeb灬D on 2021-11-27 11:32
 */
public class aFilter implements YdFilter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }
}
