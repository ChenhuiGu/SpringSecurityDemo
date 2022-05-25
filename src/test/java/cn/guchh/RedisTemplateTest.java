package cn.guchh;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;


@SpringBootTest
public class RedisTemplateTest {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void set(){
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set("谷晨辉","admin");
    }

    @Test
    public void get(){
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        System.out.println(ops.get("guchh"));
        System.out.println(ops.get("谷晨辉"));
    }


}
