package com.ardecs.cache.strategy;

import com.ardecs.cache.exceptions.KeyNotFoundException;
import java.io.File;
import java.io.Serializable;

public class LRUDisk<K, V extends Serializable> extends LRU<K, V> implements Strategy<K, V> {

    private final String fileName = "LRUCache";

    public LRUDisk(int sizeOfCache) {
        super(sizeOfCache);
        new LRU(sizeOfCache);
    }

    @Override
    public V get(K key) {
        DiskStrategy.uploadFromDisk(mapCache, fileName);
        if (mapCache.size() == 0 || !mapCache.containsKey(key)) {
            throw new KeyNotFoundException("key " + key + " not found in cache");
        }
        V value = mapCache.get(key);
        DiskStrategy.downloadToDisk(mapCache, fileName);
        return value;
    }

    @Override
    public void put(K key, V value) {
        if (mapCache.size() != 0) {
            DiskStrategy.uploadFromDisk(mapCache, fileName);
        }
        mapCache.put(key, value);
        DiskStrategy.downloadToDisk(mapCache, fileName);
    }

    @Override
    public void clear() {
        mapCache.clear();
        new File(fileName).delete();
    }
}
