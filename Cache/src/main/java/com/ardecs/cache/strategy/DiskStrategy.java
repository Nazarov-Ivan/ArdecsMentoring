package com.ardecs.cache.strategy;

import com.ardecs.cache.cache.Cache;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface DiskStrategy<K, V> {

    void downloadToDisk();
    void uploadFromDisk();
    void put(K key, V value);
    V get(K key);
    void clear();

}
