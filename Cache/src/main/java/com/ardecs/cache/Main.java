package com.ardecs.cache;

import com.ardecs.cache.cache.Cache;
import com.ardecs.cache.models.User;
import com.ardecs.cache.strategy.LFU;
import com.ardecs.cache.strategy.LFUDisk;
import com.ardecs.cache.strategy.LRU;

public class Main {
    public static void main(String[] args) {
        Cache lruCache = new Cache<>(new LFUDisk<>(3));
        lruCache.clearCache();
        User one = new User(1, "Koly");
        User two = new User(2, "Sergei");
        User three = new User(3, "Oly");
        User four = new User(4, "Zheny");
        User five = new User(5, "Katy");
        lruCache.putToCache(1,one);
        lruCache.putToCache(2,two);
        lruCache.putToCache(3,three);
        System.out.println(lruCache.getFromCache(2));
        System.out.println(lruCache.getFromCache(1));

        lruCache.putToCache(4, four);

        System.out.println(lruCache);

    }
}
