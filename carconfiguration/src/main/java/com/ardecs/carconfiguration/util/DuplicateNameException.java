package com.ardecs.carconfiguration.util;

import java.util.function.Consumer;

/**
 * @author Nazarov Ivan
 * @date 10/23/2022
 */
public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(String message) {
        super(message);
    }
}
