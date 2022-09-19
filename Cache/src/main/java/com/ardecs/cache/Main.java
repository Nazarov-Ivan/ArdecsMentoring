package com.ardecs.cache;

import com.ardecs.cache.cache.Cache;
import com.ardecs.cache.models.User;
import com.ardecs.cache.strategy.LRUDisk;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Cache lruCache = new Cache<>(new LRUDisk<>(3));
        Cache lfuCache = new Cache<>(new LRUDisk<>(2));
        User one = new User(1, "Kolya");
        User two = new User(2, "Sergei");
        User three = new User(3, "Oly");
        User four = new User(4, "Zhenya");
        User five = new User(5, "Katy");
        lruCache.putToCache(1, one);
        lruCache.putToCache(2, two);
        lruCache.putToCache(3, three);
        System.out.println(lruCache.getFromCache(1));
        System.out.println(lruCache.getFromCache(3));

        lruCache.putToCache(4, four);

        System.out.println(lruCache);




    }
}
