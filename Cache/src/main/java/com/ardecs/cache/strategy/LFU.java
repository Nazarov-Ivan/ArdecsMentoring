package com.ardecs.cache.strategy;

import com.ardecs.cache.exceptions.KeyNotFoundException;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LFU<K, V extends Serializable> implements Strategy<K, V>, Serializable {
    private final int sizeOfCache;

    protected HashMap<K, CacheEntry> mapCache;

    protected class CacheEntry implements Comparable<CacheEntry>, Serializable {
        Integer countOfUse;
        V value;

        CacheEntry(V value) {
            countOfUse = 1;
            this.value = value;
        }

        @Override
        public int compareTo(CacheEntry o) {
            return this.countOfUse.compareTo(o.countOfUse);
        }
    }

    public LFU(int sizeOfCache) {
        this.sizeOfCache = sizeOfCache;
        mapCache = new HashMap<>(sizeOfCache);
    }

    protected void add(K key, V value) {
        while (mapCache.size() >= this.sizeOfCache) {
            K minKey = mapCache.entrySet().stream()
                    .min(Map.Entry.comparingByValue(CacheEntry::compareTo)).orElse(null).getKey();
            mapCache.remove(minKey);
        }
        mapCache.put(key, new CacheEntry(value));
    }

    @Override
    public void put(K key, V value)  {
        add(key, value);
    }

    @Override
    public V get(K key) {
        if (mapCache.size() == 0) {
            throw new KeyNotFoundException("key " + key + " not found in cache");
        } else if (mapCache.containsKey(key)) {
            mapCache.get(key).countOfUse++;
            return mapCache.get(key).value;
        } else throw new KeyNotFoundException("key " + key + " not found in cache");
    }

    @Override
    public void clear() {
        mapCache.clear();
    }
}
