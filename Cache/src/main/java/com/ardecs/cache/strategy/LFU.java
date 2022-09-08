package com.ardecs.cache.strategy;

import com.ardecs.cache.cache.KeyNotFoundException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class LFU<K, V extends Serializable> implements Strategy<K, V> {
    private final int sizeOfCache;
    protected HashMap<K, V> mapCache;
    protected HashMap<K, Integer> mapCountOfUsing;

    public LFU(int sizeOfCache) {
        this.sizeOfCache = sizeOfCache;
        mapCache = new HashMap<>(sizeOfCache);
        mapCountOfUsing = new HashMap<>(sizeOfCache);
    }

    public void add(K key, V value) {
        while (mapCache.size() >= this.sizeOfCache) {
            K minKey = mapCountOfUsing.entrySet().stream()
                    .min(Map.Entry.comparingByValue(Integer::compareTo)).get().getKey();
            mapCountOfUsing.remove(minKey);
            mapCache.remove(minKey);
        }
        mapCache.put(key, value);
        mapCountOfUsing.put(key, 1);
    }

    @Override
    public void put(K key, V value) {
        add(key, value);
    }

    @Override
    public V get(K key) {
        if (mapCache.size() == 0) {
            throw new KeyNotFoundException("key "+key+" not found in cache");
        } else if (mapCache.containsKey(key)) {
            int countOfUse = mapCountOfUsing.get(key);
            countOfUse++;
            mapCountOfUsing.put(key, countOfUse);
            return mapCache.get(key);
        } else throw new KeyNotFoundException("key "+key+" not found in cache");
    }

    @Override
    public void clear() {
        mapCache.clear();
        mapCountOfUsing.clear();
    }
}
