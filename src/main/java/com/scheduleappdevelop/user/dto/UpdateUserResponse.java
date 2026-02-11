package com.scheduleappdevelop.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"userId", "name", "email"})
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
