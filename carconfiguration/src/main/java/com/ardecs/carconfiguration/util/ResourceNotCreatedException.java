package com.ardecs.carconfiguration.util;

/**
 * @author Nazarov Ivan
 * @date 10/22/2022
 */
public class ResourceNotCreatedException extends RuntimeException {
    public ResourceNotCreatedException(String message) {
        super(message);
    }
}
