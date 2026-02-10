package com.scheduleappdevelop.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneUserResponse {

    private final Long userId;
    private final String name;
    private final String email;

    public GetOneUserResponse(Long userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }
}