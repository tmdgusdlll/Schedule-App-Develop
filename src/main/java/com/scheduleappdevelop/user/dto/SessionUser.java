package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class SessionUser {

    private final Long userId;
    private final String name;
    private final String email;

    public SessionUser(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
