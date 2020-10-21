package com.example.springdemo.component;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisClockHelper {

    public void init(){

        Jedis jedis = new Jedis();
    }
}
