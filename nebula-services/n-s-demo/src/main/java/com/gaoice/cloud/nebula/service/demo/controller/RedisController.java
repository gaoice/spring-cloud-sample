package com.gaoice.cloud.nebula.service.demo.controller;

import com.gaoice.cloud.nebula.module.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoice
 */
@RestController
@RequestMapping("/redis")
public class RedisController extends AbstractController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/string")
    public String string(String s) {
        redisTemplate.opsForValue().set("a", s);
        return redisTemplate.opsForValue().get("a").toString();
    }

    @RequestMapping("/get")
    public String get() {
        return redisTemplate.opsForValue().get("a").toString();
    }
}
