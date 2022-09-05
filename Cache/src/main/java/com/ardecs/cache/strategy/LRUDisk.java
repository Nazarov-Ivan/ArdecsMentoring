package com.ardecs.cache.strategy;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class LRUDisk<K, V extends Serializable> implements Strategy<K, V>, DiskStrategy<K, V>{

    String fileName = "LRUCache";
    String fileNameHelp = "LRUHelpCache";
    private HashMap<K, V> mapCache;
    private LinkedList<K> listAgeOfUsing;
    private int sizeOfCache;

    public LRUDisk(int sizeOfCache) {
        this.sizeOfCache = sizeOfCache;
        mapCache = new HashMap<>(sizeOfCache);
        listAgeOfUsing = new LinkedList<>();
    }

    @Override
    public void downloadToDisk() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
             FileOutputStream fileOutputStreamHelp = new FileOutputStream(fileNameHelp);
             ObjectOutputStream objectOutputStreamHelp = new ObjectOutputStream(fileOutputStreamHelp)){
            objectOutputStream.writeObject(mapCache);
            objectOutputStreamHelp.writeObject(listAgeOfUsing);
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода");
        }
    }

    @Override
    public void uploadFromDisk() {
        try(FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            FileInputStream fileInputStreamHelp = new FileInputStream(fileNameHelp);
            ObjectInputStream objectInputStreamHelp = new ObjectInputStream(fileInputStreamHelp)){
            mapCache = (HashMap<K, V>) objectInputStream.readObject();
            listAgeOfUsing = (LinkedList<K>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не создан");
        } catch (IOException e) {
            System.out.println("Диск пуст");
        } catch (ClassNotFoundException e) {
            System.out.println("Класс не был найден");
        }
    }


    @Override
    public V get(K key) {
        if (mapCache.size() == 0) {
            return null;
        } else{
        uploadFromDisk();
        if (listAgeOfUsing.remove(key)) {
            listAgeOfUsing.addFirst(key);
            downloadToDisk();
            return mapCache.get(key);
        }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        listAgeOfUsing.remove(key);
        while (listAgeOfUsing.size() >= this.sizeOfCache){
            K keyForDelete = listAgeOfUsing.removeLast();
            mapCache.remove(keyForDelete);
        }
        listAgeOfUsing.addFirst(key);
        mapCache.put(key, value);
        downloadToDisk();
    }


    @Override
    public void clear() {
        mapCache.clear();
        listAgeOfUsing.clear();
        new File(fileName).delete();
        new File(fileNameHelp).delete();
    }
}
