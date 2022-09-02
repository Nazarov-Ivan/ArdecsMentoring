package com.ardecs.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LFU implements Strategy{
    private int sizeOfCache;
    private HashMap<String, String> mapCache;
    private HashMap<String, Integer> mapCountOfUsing;


    @Override
    public void add(String key, String value) {
        while (mapCache.size() >= this.sizeOfCache){
            List<Integer> listValuesCountOfUsing = new ArrayList<>(mapCountOfUsing.values());
            int minNumberOfUse = listValuesCountOfUsing.get(0);
            for (int i = 1; i < listValuesCountOfUsing.size(); i++){
                if (minNumberOfUse<listValuesCountOfUsing.get(i)){
                    minNumberOfUse = listValuesCountOfUsing.get(i);
                }
            }
            List<String> listOfMapCountKeys = new ArrayList<>(mapCountOfUsing.keySet());
            for (String keyForDelete : listOfMapCountKeys){
                if (mapCountOfUsing.get(keyForDelete) == minNumberOfUse){
                    System.out.println("Ключ " + keyForDelete +
                            " и его значение " +  mapCache.get(keyForDelete) + " удалены");
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
    public Object get(String key) {
        if (mapCache.containsKey(key)){
            int countOfUse = mapCountOfUsing.get(key);
            countOfUse++;
            mapCountOfUsing.put(key, countOfUse);
            return mapCache.get(key);}
        return null;
    }

    @Override
    public void clear() {
            mapCache.clear();
            mapCountOfUsing.clear();
            mapCache = new HashMap<>(sizeOfCache);
            mapCountOfUsing = new HashMap<>(sizeOfCache);
            System.out.println("Кеш очищен");
    }
}
