package com.yd.scala.hello.extension.adapter.remote;

import com.yd.scala.hello.extension.config.ExtensionParameter;
import com.yd.scala.hello.extension.config.ExtensionResult;
import org.apache.dubbo.rpc.service.GenericException;

public interface ExtensionGenericService {
    ExtensionResult<?> $invoke(String method, ExtensionParameter parameter) throws GenericException;
}
