package com.ardecs.carconfiguration.exceptions;

/**
 * @author Nazarov Ivan
 * @date 10/23/2022
 */
public class DuplicateNameException extends RuntimeException {
    public DuplicateNameException(String message) {
        super(message);
    }
}
