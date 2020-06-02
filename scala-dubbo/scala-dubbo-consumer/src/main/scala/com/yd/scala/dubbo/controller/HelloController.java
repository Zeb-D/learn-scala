package com.yd.scala.dubbo.controller;

import com.yd.scala.dubbo.client.IHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by Zeb灬D on 2020-05-30 22:28
 */
@RestController
public class HelloController {

    @Reference
    private IHelloService helloService;

    @RequestMapping("/sayHello")
    public String getAll(@RequestParam(name = "uid") String uid) {
        return helloService.sayHello(uid);
    }

}
