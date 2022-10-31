package com.ardecs.carconfiguration.exceptions;

/**
 * @author Nazarov Ivan
 * @date 10/22/2022
 */
public class ResourceNotFoundIdException extends RuntimeException {
    public ResourceNotFoundIdException(String message) {
        super(message);
    }
}
