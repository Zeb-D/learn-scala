package com.yd.test.handler;

import com.yd.scala.hello.handler.aspect.Zebra;
import com.yd.scala.hello.handler.spring.EnableZebra;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author created by Zeb灬D on 2021-07-21 12:13
 */
@Service
@EnableZebra
public class ZebraService {

    @Zebra("pwd")
    public User sayUser(String userName) {
        User user = new User();
        user.setUserName(userName);
        user.setPwd(new Random().nextLong() + "");
        return user;
    }

    static class User {
        public String userName;
        public String pwd;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
}
