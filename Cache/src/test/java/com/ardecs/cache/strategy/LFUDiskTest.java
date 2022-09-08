package com.ardecs.cache.strategy;

import com.ardecs.cache.cache.Cache;
import com.ardecs.cache.cache.KeyNotFoundException;
import com.ardecs.cache.models.User;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class LFUDiskTest {
    Cache lfuDiskCache = new Cache<>(new LFUDisk<>(5));
    User one = new User(1, "Kolya");
    User two = new User(2, "Sergei");
    User three = new User(3, "Oly");
    User four = new User(4, "Zhenya");
    User five = new User(5, "Katy");

    @Before
    public void setUp()  {
        lfuDiskCache.putToCache(one.getId(), one);
        lfuDiskCache.putToCache(two.getId(), two);
        lfuDiskCache.putToCache(three.getId(), three);
        lfuDiskCache.putToCache(four.getId(), four);
        lfuDiskCache.putToCache(five.getId(), five);
    }

    @Test
    public void getShouldReturnValue() {
        assertEquals(one, lfuDiskCache.getFromCache(1));
        assertEquals(two, lfuDiskCache.getFromCache(2));
        assertEquals(three, lfuDiskCache.getFromCache(3));
        assertEquals(four, lfuDiskCache.getFromCache(4));
        assertEquals(five, lfuDiskCache.getFromCache(5));

    }

    @Test(expected = KeyNotFoundException.class)
    public void getShouldReturnException() throws KeyNotFoundException {
        lfuDiskCache.getFromCache(6);
    }

    @Test(expected = RuntimeException.class)
    public void clearShouldReturnException() throws RuntimeException {
        lfuDiskCache.clearCache();
        lfuDiskCache.getFromCache(1);
    }

    @Test(expected = KeyNotFoundException.class)
    public void lfuShouldDeleteRarest() throws KeyNotFoundException {
        lfuDiskCache.getFromCache(1);
        lfuDiskCache.getFromCache(2);
        lfuDiskCache.getFromCache(3);
        lfuDiskCache.getFromCache(5);
        lfuDiskCache.putToCache(6, new User(6, "Ivan"));
        lfuDiskCache.getFromCache(4);
    }
}