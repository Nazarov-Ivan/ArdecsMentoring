package com.ardecs.cache.strategy;

import com.ardecs.cache.cache.Cache;
import com.ardecs.cache.exceptions.KeyNotFoundException;
import com.ardecs.cache.models.User;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class LFUTest {
    Cache lfuCache = new Cache<>(new LFU<>(5));
    User one = new User(1, "Kolya");
    User two = new User(2, "Sergei");
    User three = new User(3, "Oly");
    User four = new User(4, "Zhenya");
    User five = new User(5, "Katy");

    @Before
    public void setUp() throws FileNotFoundException {
        lfuCache.putToCache(one.getId(), one);
        lfuCache.putToCache(two.getId(), two);
        lfuCache.putToCache(three.getId(), three);
        lfuCache.putToCache(four.getId(), four);
        lfuCache.putToCache(five.getId(), five);
    }

    @Test
    public void getShouldReturnValue() throws FileNotFoundException {
        assertEquals(three, lfuCache.getFromCache(3));
    }

    @Test(expected = KeyNotFoundException.class)
    public void getShouldReturnException() throws KeyNotFoundException, FileNotFoundException {
        lfuCache.getFromCache(6);
    }

    @Test(expected = KeyNotFoundException.class)
    public void clearShouldReturnException() throws KeyNotFoundException, FileNotFoundException {
        lfuCache.clearCache();
        lfuCache.getFromCache(1);
    }

    @Test(expected = KeyNotFoundException.class)
    public void lfuShouldDeleteRarest() throws KeyNotFoundException, FileNotFoundException {
        lfuCache.getFromCache(1);
        lfuCache.getFromCache(2);
        lfuCache.getFromCache(3);
        lfuCache.getFromCache(5);
        lfuCache.putToCache(6, new User(6, "Ivan"));
        lfuCache.getFromCache(4);
    }
}