package com.scheduleappdevelop.config;

import com.scheduleappdevelop.exception.ServiceExcepion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceExcepion.class)
    public ResponseEntity<String> handlerServiceException(ServiceExcepion e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
