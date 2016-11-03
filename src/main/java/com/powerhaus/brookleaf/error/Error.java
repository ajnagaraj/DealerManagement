package com.powerhaus.brookleaf.error;

public class Error {
    
    private final String cause;
    
    public Error(String cause) {
        this.cause = cause;
    }
    
    public String getCause() {
        return cause;
    }
}
