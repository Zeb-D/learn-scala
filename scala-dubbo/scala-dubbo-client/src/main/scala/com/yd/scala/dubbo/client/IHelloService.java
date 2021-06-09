package com.yd.scala.dubbo.client;

import com.yd.scala.dubbo.client.domain.User;

/**
 * @author created by Zeb-D on 2019-05-08 14:37
 */
public interface IHelloService {

    void sayHello();

    String sayHello(String uid);

    User GetUser(String uid);

    void sleep(User user);

    User getNoPara();

    User sayHelloByUid(String uid);

    default String takeUid(String uid){
        return sayHello(uid);
    }
}
