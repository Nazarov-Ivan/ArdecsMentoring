package com.ardecs.cache.strategy;

import java.io.FileNotFoundException;

public interface Strategy <K, V> {
    void put(K key, V value) throws FileNotFoundException;
    V get(K key) throws FileNotFoundException;
    void clear();
}
