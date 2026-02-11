package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ServiceExcepion {
    public UserNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
