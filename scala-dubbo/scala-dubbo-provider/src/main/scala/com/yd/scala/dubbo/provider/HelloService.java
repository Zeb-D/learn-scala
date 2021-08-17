package com.yd.scala.dubbo.provider;

import com.yd.scala.dubbo.client.IHelloService;
import com.yd.scala.dubbo.client.domain.User;
import com.yd.scala.dubbo.client.exception.DubboClientException;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author created by zouyd on 2019-05-08 14:43
 */
//@Service
@org.apache.dubbo.config.annotation.Service
public class HelloService implements IHelloService {

    @Override
    public void sayHello() {
        String aa = UUID.randomUUID().toString();
        System.out.println(aa);
        ExecutorService poolExecutor = Executors.newFixedThreadPool(1);
        poolExecutor.submit(() -> System.out.println(UUID.randomUUID().toString()));
        //假设系统在执行该方法1s后，触发了jvm GC, 请问哪个对象会被回收
        System.gc();
    }

    @Override
    public String sayHello(String uid) {
        System.out.println("\n===================>" + uid);
        return uid + System.currentTimeMillis();
    }

    @Override
    public User GetUser(String uid) {
        User user = new User();
        user.setAge(new Random().nextInt());
        user.setName(uid);
//        user.setList(new Integer[]{11, 22, 3556});
        return user;
    }

    @Override
    public void sleep(User user) {
        System.out.println("=======>" + user);
        System.out.println("sleep<==========");
    }

    @Override
    public User getNoPara() {
        return this.GetUser("yd");
    }

    @Override
    public User sayHelloByUid(String uid) {
        if (new Random().nextBoolean()) {
            throw new DubboClientException("xx");
        }
        return GetUser(uid);
    }
}
