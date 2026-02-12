package com.scheduleappdevelop.user.dto;

import com.scheduleappdevelop.user.entity.User;
import lombok.Getter;

@Getter
public class GetAllUserResponse {

    private final Long userId;
    private final String name;

    public GetAllUserResponse(User user) {
        this.userId = user.getId();
        this.name = user.getName();
    }
}
