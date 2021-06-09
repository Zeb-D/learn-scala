package com.yd.scala.hello.dao.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yd.scala.hello.dao.base.UserDao;
import com.yd.scala.hello.dao.domian.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}