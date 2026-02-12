package com.scheduleappdevelop.schedule.dto;

import com.scheduleappdevelop.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetAllScheduleWithPageResponse {

    private final Long scheduleId;
    private final Long userId;
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public GetAllScheduleWithPageResponse(Schedule schedule) {
        this.scheduleId = schedule.getId();
        this.userId = schedule.getUser().getId();
        this.username = schedule.getUser().getName();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }
}
