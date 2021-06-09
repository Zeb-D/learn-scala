package com.yd.scala.helloscala;

import com.yd.scala.hello.limit.RedisLimit;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author created by Zeb灬D on 2019-12-24 15:20
 */
public class RedisTest extends BaseTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void TestWrite() {
        User user = new User();
        user.name = "Yd";
        user.age = 11;
//        redisTemplate.opsForValue().set("test:scala", user);
        System.out.println(redisTemplate.opsForValue().get("test:scala"));
    }

    @Test
    public void testRedisLimit() {
        RedisLimit.TestArgs();
        boolean result1 = RedisLimit.createLimit("aa1", 2, 60, redisTemplate);
        System.out.println(result1);
    }

    public static class User implements Serializable {
        private static final long serialVersionUID = 1;
        String name;
        String sex;
        Integer age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
