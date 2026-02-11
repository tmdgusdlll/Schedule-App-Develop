package com.scheduleappdevelop.exception;

import org.springframework.http.HttpStatus;

public class LoginUnauthorizedException extends ServiceExcepion {
    public LoginUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
