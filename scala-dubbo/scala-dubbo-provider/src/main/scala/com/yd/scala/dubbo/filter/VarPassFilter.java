package com.yd.scala.dubbo.filter;

import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author created by Zeb灬D on 2021-11-27 11:32
 */
public class VarPassFilter implements YdFilter {
    public static final String KEY_TRACE_ID = "traceId";
    private Protocol dubbo;//试试这个的spi能不能注入

    public void setDubbo(Protocol dubbo) {
        this.dubbo = dubbo;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        MDC.put(KEY_TRACE_ID, UUID.randomUUID().toString());
        try {
            return invoker.invoke(invocation);
        } finally {
            MDC.remove(KEY_TRACE_ID);
        }
    }
}
