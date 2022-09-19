package com.ardecs.cache.strategy;

import com.ardecs.cache.exceptions.KeyNotFoundException;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUDisk<K, V extends Serializable> extends LRU<K, V> implements Strategy<K, V> {

    private final String fileName = "LRUCache";

    public LRUDisk(int sizeOfCache) {
        super(sizeOfCache);
        new LRU(sizeOfCache);
    }

    @Override
    public V get(K key) {
        DiskStrategy.uploadFromDisk(mapCache, fileName);
        if (mapCache.size() == 0) {
            throw new KeyNotFoundException("key " + key + " not found in cache");
        }
        if (!mapCache.containsKey(key)) {
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
