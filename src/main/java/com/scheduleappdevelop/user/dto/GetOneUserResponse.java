package com.scheduleappdevelop.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scheduleappdevelop.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"userId", "name", "email"})
public class GetOneUserResponse {

    private final Long userId;
    private final String name;
    private final String email;

    public GetOneUserResponse(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}