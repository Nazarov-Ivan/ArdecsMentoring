package com.ardecs.carconfiguration.exceptions;

import java.util.function.Supplier;

/**
 * @author Nazarov Ivan
 * @date 10/26/2022
 */
public class ResourceNotFoundModelComplectException extends RuntimeException {
    public ResourceNotFoundModelComplectException(String message) {
        super(message);
    }

    public static Supplier<ResourceNotFoundModelComplectException> resourceNotFoundModelComplectException(String message) {
        return () -> new ResourceNotFoundModelComplectException(message);
    }
}
