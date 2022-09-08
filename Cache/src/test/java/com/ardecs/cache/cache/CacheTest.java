package com.ardecs.cache.cache;

import com.ardecs.cache.strategy.LFU;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {
    Cache cache = new Cache<>(new LFU<>(5));
    @Test(expected = KeyValueIsNullException.class)
    public void nullKeyShouldReturnException() throws KeyValueIsNullException {
       cache.putToCache(null, 1);
    }
    @Test(expected = KeyValueIsNullException.class)
    public void nullValueShouldReturnException() throws KeyValueIsNullException {
        cache.putToCache(1, null);
    }
}