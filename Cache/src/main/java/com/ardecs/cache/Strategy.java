package com.ardecs.cache;

public interface Strategy {
    void add(String key, String value);

    Object get(String key);

    void clear();
}
