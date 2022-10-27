package com.ardecs.carconfiguration.exceptions;

import java.util.function.Supplier;

/**
 * @author Nazarov Ivan
 * @date 10/22/2022
 */
public class ResourceNotFoundIdException extends RuntimeException {
    public ResourceNotFoundIdException(String message) {
        super(message);
    }

    public static Supplier<ResourceNotFoundIdException> resourceNotFoundIdException(String message) {
        return () -> new ResourceNotFoundIdException(message);
    }
}
