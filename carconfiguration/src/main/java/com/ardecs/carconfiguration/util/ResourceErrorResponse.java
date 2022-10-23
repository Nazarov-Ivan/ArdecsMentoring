package com.ardecs.carconfiguration.util;

/**
 * @author Nazarov Ivan
 * @date 10/22/2022
 */
public class ResourceErrorResponse {
    private String message;
    private long timestamp;

    public ResourceErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
