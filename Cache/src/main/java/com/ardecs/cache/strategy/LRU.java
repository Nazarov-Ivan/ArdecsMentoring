package com.ardecs.cache.strategy;

import com.ardecs.cache.exceptions.KeyNotFoundException;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K, V extends Serializable> implements Strategy<K, V>, Serializable {
    protected LinkedHashMap<K, V> mapCache;
    private final Float loadFactor = 0.75f;

    public LRU(int sizeOfCache) {
        mapCache = new LinkedHashMap<K, V>(sizeOfCache, loadFactor, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return sizeOfCache < mapCache.size();
            }
        };
    }

    @Override
    public void put(K key, V value) {
        mapCache.put(key, value);
    }

    @Override
    public V get(K key) {
        if (mapCache.size() == 0 || !mapCache.containsKey(key)) {
            throw new KeyNotFoundException("key " + key + " not found in cache");
        }
        return mapCache.get(key);
    }

    @Override
    public void clear() {
        mapCache.clear();
    }
}
