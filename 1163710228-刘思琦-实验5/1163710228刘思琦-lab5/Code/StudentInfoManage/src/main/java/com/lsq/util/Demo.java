package com.lsq.util;

import redis.clients.jedis.Jedis;

public class Demo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        jedis.set("foo", "bar");
        System.out.println(jedis.get("foo"));
    }

}