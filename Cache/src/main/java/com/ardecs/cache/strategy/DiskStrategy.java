package com.ardecs.cache.strategy;

public interface DiskStrategy<K, V> extends Strategy<K, V> {
    void downloadToDisk();

    void uploadFromDisk();
}
