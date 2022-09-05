package com.ardecs.cache.strategy;

import java.io.Serializable;

public interface Strategy <K, V>{

    void add(K key, V value);
    void put(K key, V value);
    V get(K key);
    void clear();
}
