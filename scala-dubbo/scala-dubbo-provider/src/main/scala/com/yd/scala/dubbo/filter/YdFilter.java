package com.yd.scala.dubbo.filter;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

/**
 * @author created by Zeb灬D on 2021-11-27 13:21
 */
@SPI("varpass")
public interface YdFilter {
    @Adaptive
    //用于字节码生成，生成的是个硬编码了，必须是arg这种，debug需要注意下，但编译后会搽除
    //生成的Adaptive方法字节码会去找getExtension(name)找到一个具体实现，和代理差不多
    Result invoke(Invoker<?> arg0, Invocation arg1) throws RpcException;
}
