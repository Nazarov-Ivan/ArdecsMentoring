package com.ardecs.cache.cache;

import com.ardecs.cache.exceptions.KeyValueIsNullException;
import com.ardecs.cache.strategy.LFU;
import org.junit.Test;

import java.io.FileNotFoundException;

public class CacheTest {
    Cache cache = new Cache<>(new LFU<>(5));
    @Test(expected = KeyValueIsNullException.class)
    public void nullKeyShouldReturnException() throws KeyValueIsNullException, FileNotFoundException {
       cache.putToCache(null, 1);
    }
    @Test(expected = KeyValueIsNullException.class)
    public void nullValueShouldReturnException() throws KeyValueIsNullException, FileNotFoundException {
        cache.putToCache(1, null);
    }
}