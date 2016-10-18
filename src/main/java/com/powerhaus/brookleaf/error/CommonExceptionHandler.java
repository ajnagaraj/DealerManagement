package com.powerhaus.brookleaf.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public String handleExceptions() {
        return "/errors";
    }
}
