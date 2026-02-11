package com.scheduleappdevelop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceExcepion extends RuntimeException {

    private final HttpStatus status;

    public ServiceExcepion(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
