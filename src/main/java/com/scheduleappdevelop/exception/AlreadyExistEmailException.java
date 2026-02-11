package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistEmailException extends ServiceExcepion {
    public AlreadyExistEmailException(String message) {
        super(HttpStatus.CONFLICT, message);

    }
}
