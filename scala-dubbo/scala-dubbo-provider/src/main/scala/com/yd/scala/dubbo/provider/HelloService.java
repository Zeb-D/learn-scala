package com.yd.scala.dubbo.provider;

import com.yd.scala.dubbo.client.IHelloService;
import com.yd.scala.dubbo.client.domain.User;
import com.yd.scala.dubbo.client.exception.DubboClientException;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author created by zouyd on 2019-05-08 14:43
 */
@Service
//@org.apache.dubbo.config.annotation.Service
public class HelloService implements IHelloService {
    @Override
    public String sayHello(String uid) {
        System.out.println("\n===================>" + uid);
        return uid + System.currentTimeMillis();
    }

    @Override
    public User getUser(String uid) {
        User user = new User();
        user.setAge(new Random().nextInt());
        user.setName(uid);
        user.setList(new Integer[]{11, 22, 3556});
        return user;
    }

    @Override
    public void sleep(User user) {
        System.out.println("=======>" + user);
        System.out.println("sleep<==========");
    }

    @Override
    public User getNoPara() {
        return this.getUser("yd");
    }

    @Override
    public User sayHelloByUid(String uid) {
        if (new Random().nextBoolean()) {
            throw new DubboClientException("xx");
        }
        return getUser(uid);
    }
}
