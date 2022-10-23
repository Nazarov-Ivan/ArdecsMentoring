package com.ardecs.carconfiguration.util;

import java.util.function.Supplier;

/**
 * @author Nazarov Ivan
 * @date 10/23/2022
 */
public class ResourceNotFoundNameException extends RuntimeException {
    public ResourceNotFoundNameException(String message) {
        super(message);
    }

    public static Supplier<ResourceNotFoundNameException> resourceNotFoundNameException(String message) {
        return () -> new ResourceNotFoundNameException(message);
    }
}
