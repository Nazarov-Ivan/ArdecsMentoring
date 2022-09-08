package com.ardecs.cache.strategy;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUmap<K, V> extends LinkedHashMap<K, V> {
    private final int cacheSize;

    public LRUmap(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }
}
