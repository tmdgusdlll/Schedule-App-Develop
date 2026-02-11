package com.scheduleappdevelop.schedule.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"scheduleId", "userId", "username", "title"})
public class GetAllScheduleResponse {

    private final Long scheduleId;
    private final Long userId;
    private final String username;
    private final String title;
//    private final String content;
//    private final LocalDateTime createdAt;
//    private final LocalDateTime modifiedAt;

    public GetAllScheduleResponse(Long id, Long userId, String username, String title) {
        this.scheduleId = id;
        this.userId = userId;
        this.username = username;
        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
    }
}
