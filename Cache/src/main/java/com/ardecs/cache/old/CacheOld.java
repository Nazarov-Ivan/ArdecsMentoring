package com.ardecs.cache.old;

public interface CacheOld {
    void add(String key, String value);

    Object get(String key);

    void allDelete();
}
