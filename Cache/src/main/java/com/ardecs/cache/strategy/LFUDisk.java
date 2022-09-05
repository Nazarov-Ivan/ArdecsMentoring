package com.ardecs.cache.strategy;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LFUDisk<K, V extends Serializable> extends LFU <K, V> implements DiskStrategy<K, V>{
    String fileName = "LFUCache";
    String fileNameHelp = "LFUHelpCache";

    public LFUDisk(int sizeOfCache) {
        super(sizeOfCache);
        new LFU(sizeOfCache);
    }


    public void downloadToDisk() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
             FileOutputStream fileOutputStreamHelp = new FileOutputStream(fileNameHelp);
             ObjectOutputStream objectOutputStreamHelp = new ObjectOutputStream(fileOutputStreamHelp)){
            objectOutputStream.writeObject(mapCache);
            objectOutputStreamHelp.writeObject(mapCountOfUsing);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }


    public void uploadFromDisk() {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            FileInputStream fileInputStreamHelp = new FileInputStream(fileNameHelp);
            ObjectInputStream objectInputStreamHelp = new ObjectInputStream(fileInputStreamHelp)){
            HashMap<K, V> mapFromDisk = (HashMap<K, V>) objectInputStream.readObject();
            mapCache = mapFromDisk;
            HashMap<K, Integer> mapCount = (HashMap<K, Integer>) objectInputStreamHelp.readObject();
            if (mapCount != null) {
                mapCountOfUsing = mapCount;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не создан");
        } catch (IOException e) {
            System.out.println("Диск пуст");
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не был найден");
        }
    }

    @Override
    public void put(K key, V value) {
        add(key, value);
        downloadToDisk();
    }

    @Override
    public V get(K key) {
        uploadFromDisk();
        if (mapCache.size() == 0) {
            return null;
        } else if (mapCache.containsKey(key)){
                int countOfUse = mapCountOfUsing.get(key);
                countOfUse++;
                mapCountOfUsing.put(key, countOfUse);
                downloadToDisk();
                return mapCache.get(key);
            }
        else return null;
    }

    @Override
    public void clear() {
        mapCache.clear();
        mapCountOfUsing.clear();
        new File(fileName).delete();
        new File(fileNameHelp).delete();
    }

}
