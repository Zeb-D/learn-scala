package com.yd.scala.helloscala;

import com.yd.scala.hello.dao.base.UserDao;
import com.yd.scala.hello.dao.domian.User;
import com.yd.scala.hello.dao.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author created by Zeb灬D on 2021-02-05 11:55
 */
public class UserServiceTest extends BaseTest {
    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    @Test
    public void TestUserDao() {
        userDao.selectById(1);
    }

    @Test
    public void testList() {
        List<User> userList = userService.list();
        System.out.println(userList);
        if (userList.size()>0){
            userService.removeByIds(userList.stream().map(User::getId).collect(Collectors.toList()));
        }
    }

    Random r = new Random(System.currentTimeMillis());

    @Test
    public void testAdd() {
        User user = new User();
        user.setName("Yd" + r.nextDouble());
        user.setPassword(r.nextInt() + "");
        boolean s = userService.save(user);
        System.out.println(s);
    }
}
