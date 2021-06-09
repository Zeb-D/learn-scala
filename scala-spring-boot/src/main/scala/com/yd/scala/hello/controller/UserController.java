package com.yd.scala.hello.controller;

import com.yd.scala.hello.dao.domian.User;
import com.yd.scala.hello.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getAll")
    public String getAll() {
        List<User> list = userService.list();
        System.out.println("list:" + list);
        return list.toString();
    }

    @RequestMapping("/insert")
    public String insert() {
        User user = new User();
        // 不设置id的话，会自动生成一个UUID
//        user.setId(new Date().getTime() + "");
        user.setName("aaa");
        user.setPassword("bbb");
        boolean save = userService.save(user);
        return getAll();
    }
}