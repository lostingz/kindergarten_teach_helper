/**
 * Chsi
 * Created on 2016年6月29日
 */
package com.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.redis.User;

@Controller
@EnableAutoConfiguration
public class RedisTest {

    @Autowired
    private RedisTemplate<String, List<User>> redisTemplate;

    @RequestMapping(value = "/redistest")
    @ResponseBody
    public void test() {
        // // 保存字符串
        // redisTemplate.opsForValue().set("aaa", "111");
        // System.out.println(redisTemplate.opsForValue().get("aaa"));
        User user = new User("超人", 20);
        List<User> l = new ArrayList<User>();
        l.add(user);
        l.add(user);
        l.add(user);
        redisTemplate.opsForValue().set("list", l);
        User u = redisTemplate.opsForValue().get("list").get(0);
        System.out.println(u.getAge());
    }
}