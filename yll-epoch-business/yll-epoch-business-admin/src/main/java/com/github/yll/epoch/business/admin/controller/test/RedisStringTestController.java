package com.github.yll.epoch.business.admin.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author luliang_yu
 * @date 2018-12-04
 */
@Controller
@RequestMapping("/redis")
public class RedisStringTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/setget")
    public @ResponseBody String env(String param){
        redisTemplate.opsForValue().set("testenv", param);
        String str = redisTemplate.opsForValue().get("testenv");
        redisTemplate.convertAndSend("news", "hello,world");
        return str;
    }
}
