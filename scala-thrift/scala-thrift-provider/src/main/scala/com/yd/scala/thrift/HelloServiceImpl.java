package com.yd.scala.thrift;

import com.yd.scala.thrift.service.HelloService;
import org.apache.thrift.TException;

public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String helloString(String s) throws TException {
        return "Hello==>" + s;
    }
}