package com.ardecs.cache.strategy;

public interface Strategy <K, V> {
    void put(K key, V value);
    V get(K key);
    void clear();
}
