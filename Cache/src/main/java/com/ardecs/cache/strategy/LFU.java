package com.ardecs.cache.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LFU<K, V extends Serializable> implements Strategy <K, V>{
    private int sizeOfCache;
    protected HashMap<K, V> mapCache;
    protected HashMap<K, Integer> mapCountOfUsing;

    public LFU(int sizeOfCache) {
        this.sizeOfCache = sizeOfCache;
        mapCache = new HashMap<>(sizeOfCache);
        mapCountOfUsing = new HashMap<>(sizeOfCache);
    }


    public void add(K key, V value) {
        while (mapCache.size() >= this.sizeOfCache){
            List<Integer> listValuesCountOfUsing = new ArrayList<>(mapCountOfUsing.values());
            List<K> listOfMapCountKeys = new ArrayList<>(mapCountOfUsing.keySet());
            Integer minNumberOfUse = listValuesCountOfUsing.stream().min(Math::min).orElse(listValuesCountOfUsing.get(0));
            for (K keyForDelete : listOfMapCountKeys){
                if (mapCountOfUsing.get(keyForDelete) == minNumberOfUse){
                    mapCache.remove(keyForDelete);
                    mapCountOfUsing.remove(keyForDelete);
                    break;
                }
            }
        }
        mapCache.put(key, value);
        mapCountOfUsing.put(key,1);
    }

    @Override
    public void put(K key, V value) {
        add(key, value);
    }

    @Override
    public V get(K key) {
        if (mapCache.size() == 0) {
            return null;
        } else if (mapCache.containsKey(key)){
            int countOfUse = mapCountOfUsing.get(key);
            countOfUse++;
            mapCountOfUsing.put(key, countOfUse);
            return mapCache.get(key);}
        else return null;
    }

    @Override
    public void clear() {
            mapCache.clear();
            mapCountOfUsing.clear();
    }
}
