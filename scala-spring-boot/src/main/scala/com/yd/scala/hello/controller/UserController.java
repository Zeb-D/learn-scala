package com.yd.scala.hello.controller;

import com.yd.scala.hello.dao.User;
import com.yd.scala.hello.dao.UserService;
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
        user.setUserName("aaa");
        user.setUserPassword("bbb");
        boolean save = userService.save(user);
        return getAll();
    }
}