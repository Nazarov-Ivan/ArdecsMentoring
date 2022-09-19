package com.ardecs.cache.cache;

import com.ardecs.cache.exceptions.KeyValueIsNullException;
import com.ardecs.cache.strategy.Strategy;

import java.io.FileNotFoundException;

public class Cache<K, V> {
    private final Strategy<K, V> strategy;

    public Cache(Strategy<K, V> strategy) {
        this.strategy = strategy;
    }

    public void putToCache(K key, V value) throws FileNotFoundException {
        if ((value == null) || (key == null)) {
            throw new KeyValueIsNullException("value and key can't be null");
        }
        strategy.put(key, value);
    }

    public V getFromCache(K key) throws FileNotFoundException {
        return strategy.get(key);
    }

    public void clearCache() {
        strategy.clear();
    }
}
