package com.scheduleappdevelop.schedule.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scheduleappdevelop.schedule.entity.Schedule;
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

    public GetAllScheduleResponse(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.username = schedule.getUser().getName();
        this.title = schedule.getTitle();
//        this.content = content;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
    }
}
