package com.scheduleappdevelop.schedule.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({"scheduleId", "userId", "username", "title", "content", "createdAt", "modifiedAt"})
public class UpdateScheduleResponse {

    private final Long scheduleId;
    private final Long userId;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

//    public UpdateScheduleResponse(Long id, Long userId, String username, String title, String content,
//                                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
//        this.id = id;
//        this.userId = userId;
//        this.username = username;
//        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//    }

    public UpdateScheduleResponse(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.username = schedule.getUser().getName();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
