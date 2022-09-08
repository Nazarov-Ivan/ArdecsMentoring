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
import java.util.HashMap;

public class LFUDisk<K, V extends Serializable> extends LFU<K, V> implements DiskStrategy<K, V> {
    private final String fileName = "LFUCache";
    private final String fileNameHelp = "LFUHelpCache";

    private final static Logger LOGGER = LoggerFactory.getLogger(LFUDisk.class);

    public LFUDisk(int sizeOfCache) {
        super(sizeOfCache);
        new LFU(sizeOfCache);
    }

    public void downloadToDisk() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
             ObjectOutputStream objectOutputStreamHelp = new ObjectOutputStream(new FileOutputStream(fileNameHelp))) {
            objectOutputStream.writeObject(mapCache);
            objectOutputStreamHelp.writeObject(mapCountOfUsing);
        } catch (IOException e) {
            LOGGER.error("Cache didn't save to disk");
            throw new RuntimeException(e);
        }
    }

    public void uploadFromDisk() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
             ObjectInputStream objectInputStreamHelp = new ObjectInputStream(new FileInputStream(fileNameHelp))) {
            mapCache = (HashMap<K, V>) objectInputStream.readObject();
            HashMap<K, Integer> mapCount = (HashMap<K, Integer>) objectInputStreamHelp.readObject();
            if (mapCount != null) {
                mapCountOfUsing = mapCount;
            }
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
    public void put(K key, V value) {
        add(key, value);
        downloadToDisk();
    }

    @Override
    public V get(K key) {
        uploadFromDisk();
        if (mapCache.size() == 0) {
            throw new KeyNotFoundException("key "+key+" not found in cache");
        } else if (mapCache.containsKey(key)) {
            int countOfUse = mapCountOfUsing.get(key);
            countOfUse++;
            mapCountOfUsing.put(key, countOfUse);
            downloadToDisk();
            return mapCache.get(key);
        } else throw new KeyNotFoundException("key "+key+" not found in cache");
    }

    @Override
    public void clear() {
        mapCache.clear();
        mapCountOfUsing.clear();
        new File(fileName).delete();
        new File(fileNameHelp).delete();
    }

}
