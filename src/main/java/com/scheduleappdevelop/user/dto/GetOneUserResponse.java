package com.scheduleappdevelop.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"userId", "name", "email"})
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