package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserResponse {

    private final Long userId;
    private final String name;
    private final String email;

    public UpdateUserResponse(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}
