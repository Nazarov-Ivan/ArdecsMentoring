package com.ardecs.cache.cache;

import com.ardecs.cache.strategy.*;

public class Cache <K, V>{
    private Strategy strategy;
    private int size;
    private typeOfStorage storage;
    private typeOfStrategy strategyType;

    public Cache(typeOfStrategy strategyType, int size, typeOfStorage storage) {
        this.strategyType = strategyType;
        this.size = size;
        this.storage = storage;
        if (strategyType == typeOfStrategy.LFU){
            if (storage == typeOfStorage.RAM){
                strategy = new LFU(size);
            }
            else {
                strategy = new LFUDisk(size);
            }
        }
        if (strategyType == typeOfStrategy.LRU){
            if (storage == typeOfStorage.RAM){
                strategy = new LRU();
            } else strategy = new LRUDisk();
        }
    }

    public void putToCache(K key, V value){
        if ((value == null) || (key == null)) {
            throw new KeyValueIsNullException("value and key can't be null");
        }
        strategy.put(key, value);
    };
    public V getFromCache(K key){
        V value = (V) strategy.get(key);
        return value;
    }
    public void clearCache(){
        strategy.clear();
    }


}
