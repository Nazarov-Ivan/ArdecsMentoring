package com.ardecs.cache.cache;

import com.ardecs.cache.strategy.*;

public class Cache<K, V> {
    private Strategy<K, V> strategy;

    public Cache(Strategy<K, V> strategy) {
        this.strategy = strategy;
    }

    public void putToCache(K key, V value) {
        if ((value == null) || (key == null)) {
            throw new KeyValueIsNullException("value and key can't be null");
        }
        strategy.put(key, value);
    }

    public V getFromCache(K key) {
        return strategy.get(key);
    }

    public void clearCache() {
        strategy.clear();
    }


}
