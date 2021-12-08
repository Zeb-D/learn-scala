package com.yd.scala.helloscala;

import com.alibaba.fastjson.JSONObject;
import com.yd.scala.hello.limit.RedisBloomFilter;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author created by Zeb灬D on 2019-12-24 15:20
 */
public class RedisBloomFilterTest extends BaseTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void TestWrite() {
        redisTemplate.opsForValue().set("test:scala1", 1);
        System.out.println(redisTemplate.opsForValue().get("test:scala1"));
    }

    int k = 14;
    int bits = 1_0000;

    @Test
    public void testLocations() {
        int[] locations = RedisBloomFilter.getLocations("aa", k, bits);
        System.out.println(JSONObject.toJSONString(locations));
    }

    @Test
    public void testBloomFilter() {
        String key = "test-scala-bloom";
        int[] locations = RedisBloomFilter.getLocations("a", k, bits);
        System.out.println(JSONObject.toJSONString(locations));
        boolean s = RedisBloomFilter.set("test-scala-bloom", locations, redisTemplate);
        System.out.println(s);
        s = RedisBloomFilter.check(key, locations, redisTemplate);
        System.out.println(s);

        locations = RedisBloomFilter.getLocations("abc", k, bits);
        System.out.println(JSONObject.toJSONString(locations));
        s = RedisBloomFilter.check(key, locations, redisTemplate);
        System.out.println(s);

        //System.out.println(redisTemplate.opsForValue().get(key));//乱码导致序列化报错
    }

    // 添加元素
    public void Add(String str) {

    }

}
