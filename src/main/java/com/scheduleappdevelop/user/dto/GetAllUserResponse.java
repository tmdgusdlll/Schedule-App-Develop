package com.scheduleappdevelop.user.dto;

import lombok.Getter;

@Getter
public class GetAllUserResponse {

    private final Long userId;
    private final String name;

    public GetAllUserResponse(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
