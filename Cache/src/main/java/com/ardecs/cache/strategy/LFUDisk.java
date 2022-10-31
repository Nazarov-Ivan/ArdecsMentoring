package com.ardecs.cache.strategy;

import com.ardecs.cache.exceptions.KeyNotFoundException;
import java.io.File;
import java.io.Serializable;

public class LFUDisk<K, V extends Serializable> extends LFU<K, V> implements Strategy<K, V> {
    private final String fileName = "LFUCache";

    public LFUDisk(int sizeOfCache) {
        super(sizeOfCache);
        new LFU(sizeOfCache);
    }

    @Override
    public void put(K key, V value)  {
        if (mapCache.size() != 0) {
            mapCache = DiskStrategy.uploadFromDisk(mapCache, fileName);
        }
        add(key, value);
        DiskStrategy.downloadToDisk(mapCache, fileName);
    }

    @Override
    public V get(K key) {
        mapCache = DiskStrategy.uploadFromDisk(mapCache, fileName);
        if (mapCache.size() == 0 || !mapCache.containsKey(key)) {
            throw new KeyNotFoundException("key " + key + " not found in cache");
        } else {
            mapCache.get(key).countOfUse++;
            DiskStrategy.downloadToDisk(mapCache, fileName);
            return mapCache.get(key).value;
        }
    }

    @Override
    public void clear() {
        mapCache.clear();
        new File(fileName).delete();
    }
}
