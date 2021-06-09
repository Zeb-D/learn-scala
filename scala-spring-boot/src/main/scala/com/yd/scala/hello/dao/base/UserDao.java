package com.yd.scala.hello.dao.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yd.scala.hello.dao.domian.User;

//@Repository//@MapperScan("com.yd.scala.hello.dao")// 二选一
//@Mapper
public interface UserDao extends BaseMapper<User> {
}