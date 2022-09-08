package com.ardecs.cache.strategy;

import com.ardecs.cache.cache.KeyNotFoundException;
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

public class LRUDisk<K, V extends Serializable> extends LRU<K, V> implements DiskStrategy<K, V> {

    private final String fileName = "LRUCache";
    private Map<K, V> mapCache;
    private final static Logger LOGGER = LoggerFactory.getLogger(LRUDisk.class);

    public LRUDisk(int sizeOfCache) {
        super(sizeOfCache);
        mapCache = new LRUmap<K, V>(sizeOfCache);
    }

    @Override
    public void downloadToDisk() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));) {
            objectOutputStream.writeObject(mapCache);
        } catch (IOException e) {
            LOGGER.error("Cache didn't save to disk");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void uploadFromDisk() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            mapCache = (LinkedHashMap<K, V>) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            LOGGER.error("File not found");
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("Disk is empty");
        } catch (ClassNotFoundException e) {
            LOGGER.error("Class not found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public V get(K key) {
        if (mapCache.size() == 0) {
            throw new KeyNotFoundException("key " + key + " not found in cache");
        }
        if (!mapCache.containsKey(key)) {
            throw new KeyNotFoundException("key " + key + " not found in cache");
        }
        uploadFromDisk();
        V value = mapCache.get(key);
        mapCache.remove(key);
        mapCache.put(key, value);
        downloadToDisk();
        return value;
    }

    @Override
    public void put(K key, V value) {
        mapCache.put(key, value);
        downloadToDisk();
    }

    @Override
    public void clear() {
        mapCache.clear();
        new File(fileName).delete();
    }
}
