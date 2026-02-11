package com.scheduleappdevelop.user.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scheduleappdevelop.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"userId", "name", "email", "createdAt", "modifiedAt"})
public class CreateUserResponse {

    private final Long userId;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreateUserResponse(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
