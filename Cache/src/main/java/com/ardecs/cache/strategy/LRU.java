package com.ardecs.cache.strategy;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRU <K, V extends Serializable> implements Strategy <K, V>{
    private LinkedHashMap<K, V> mapCache;
    private int sizeOfCache;

    public LRU(int sizeOfCache) {
        this.sizeOfCache = sizeOfCache;
        mapCache = new LinkedHashMap<K, V>(sizeOfCache, .75f, true){
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
        return mapCache.get(key);
    }

    @Override
    public void clear() {
        mapCache.clear();
    }
}
