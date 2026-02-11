package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class SessionUser {

    private final Long userId;
    private final String email;
    private final String password;

    public SessionUser(Long userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
}
