package com.ardecs.cache.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public abstract class DiskStrategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiskStrategy.class);

    public static void downloadToDisk(HashMap mapCache, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            objectOutputStream.writeObject(mapCache);
        } catch (IOException e) {
            LOGGER.error("Cache didn't save to disk", e);
            throw new RuntimeException(e);
        }
    }

    public static void uploadFromDisk(HashMap mapCache, String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            mapCache = (HashMap) objectInputStream.readObject();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            LOGGER.error("File or class not found", ex);
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            LOGGER.error("Disk is empty", ex);
        }
    }

}
