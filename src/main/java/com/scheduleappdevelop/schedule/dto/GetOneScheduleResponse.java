package com.scheduleappdevelop.schedule.dto;

import com.scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneScheduleResponse {

    private final Long id;
    private final Long userId;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

//    public GetOneScheduleResponse(Long id, Long userId, String username, String title, String content,
//                                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
//        this.id = id;
//        this.userId = userId;
//        this.username = username;
//        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//    }

    public GetOneScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.username = schedule.getUser().getName();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
