package com.ardecs.cache.strategy;

import com.ardecs.cache.exceptions.KeyNotFoundException;
import com.sun.deploy.cache.CacheEntry;
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
import java.util.HashMap;

public class LFUDisk<K, V extends Serializable> extends LFU<K, V> implements Strategy<K, V> {
    private final String fileName = "LFUCache";

    public LFUDisk(int sizeOfCache) {
        super(sizeOfCache);
        new LFU(sizeOfCache);
    }

    @Override
    public void put(K key, V value)  {
        if (mapCache.size() != 0) {
            DiskStrategy.uploadFromDisk(mapCache, fileName);
        }
        add(key, value);
        DiskStrategy.downloadToDisk(mapCache, fileName);
    }

    @Override
    public V get(K key) {
        DiskStrategy.uploadFromDisk(mapCache, fileName);
        if (mapCache.size() == 0) {
            throw new KeyNotFoundException("key "+key+" not found in cache");
        } else if (mapCache.containsKey(key)) {
            mapCache.get(key).countOfUse++;
            DiskStrategy.downloadToDisk(mapCache, fileName);
            return mapCache.get(key).value;
        } else throw new KeyNotFoundException("key "+key+" not found in cache");
    }

    @Override
    public void clear() {
        mapCache.clear();
        new File(fileName).delete();
    }
}
