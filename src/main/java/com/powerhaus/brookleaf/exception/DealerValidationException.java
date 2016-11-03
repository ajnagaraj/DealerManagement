package com.powerhaus.brookleaf.exception;

import org.springframework.validation.Errors;

import java.util.Optional;

public class DealerValidationException extends RuntimeException {
    
    private Errors errors;
    
    public DealerValidationException(Errors errors) {
        this.errors = errors;
    }
    
    public DealerValidationException(String message) {
        super(message);
    }
    
    public DealerValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public Optional<Errors> getErrors() {
        return Optional.ofNullable(errors);
    }
}
