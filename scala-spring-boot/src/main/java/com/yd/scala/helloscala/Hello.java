package com.yd.scala.helloscala;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author created by zouyd on 2019-06-10 11:04
 */
@RestController
public class Hello {

    @GetMapping(value = "/hello1")
    public String hello(){
        return "hello scala";
    }
}
