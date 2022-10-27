package com.ardecs.carconfiguration.exceptions;

import java.util.function.Supplier;

/**
 * @author Nazarov Ivan
 * @date 10/26/2022
 */
public class DuplicateModelComplectException extends RuntimeException {
    public  DuplicateModelComplectException(String message) {
        super(message);
    }

    public static Supplier<DuplicateModelComplectException> duplicateModelComplectException(String message) {
        return () -> new DuplicateModelComplectException(message);
    }
}
