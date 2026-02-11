package com.scheduleappdevelop.schedule.dto;

import com.scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

// POST로 들어온 요청에 대한 응답 dto
@Getter
public class CreateScheduleResponse {

    private final Long id;
    private final Long userId;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

//    public CreateScheduleResponse(Long id, Long userId, String username, String title, String content,
//                                  LocalDateTime createdAt, LocalDateTime modifiedAt) {
//        this.id = id;
//        this.userId = userId;
//        this.username = username;
//        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
//    }

    public CreateScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.username = schedule.getUser().getName();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
