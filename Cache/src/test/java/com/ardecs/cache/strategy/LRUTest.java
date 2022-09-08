package com.ardecs.cache.strategy;

import com.ardecs.cache.cache.Cache;
import com.ardecs.cache.cache.KeyNotFoundException;
import com.ardecs.cache.models.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LRUTest {
    Cache lruCache = new Cache<>(new LRU<>(5));
    User one = new User(1, "Kolya");
    User two = new User(2, "Sergei");
    User three = new User(3, "Oly");
    User four = new User(4, "Zhenya");
    User five = new User(5, "Katy");

    @Before
    public void setUp() throws Exception {
        lruCache.putToCache(one.getId(), one);
        lruCache.putToCache(two.getId(), two);
        lruCache.putToCache(three.getId(), three);
        lruCache.putToCache(four.getId(), four);
        lruCache.putToCache(five.getId(), five);
    }

    @Test
    public void getShouldReturnValue() {
        assertEquals(three, lruCache.getFromCache(3));
    }

    @Test(expected = KeyNotFoundException.class)
    public void getShouldReturnException() throws KeyNotFoundException {
        lruCache.getFromCache(6);
    }

    @Test(expected = KeyNotFoundException.class)
    public void clearShouldReturnException() throws KeyNotFoundException {
        lruCache.clearCache();
        lruCache.getFromCache(1);
    }

    @Test(expected = KeyNotFoundException.class)
    public void lfuShouldDeleteRarest() throws KeyNotFoundException {
        lruCache.getFromCache(1);
        lruCache.getFromCache(1);
        lruCache.getFromCache(1);
        lruCache.getFromCache(2);
        lruCache.getFromCache(3);
        lruCache.getFromCache(4);
        lruCache.getFromCache(5);
        lruCache.putToCache(6, new User(6, "Ivan"));
        lruCache.getFromCache(1);
    }

}