package com.scheduleappdevelop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetAllScheduleResponse {

    private final Long id;
    private final String author;
    private final String title;
//    private final String content;
//    private final LocalDateTime createdAt;
//    private final LocalDateTime modifiedAt;

    public GetAllScheduleResponse(Long id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//        this.modifiedAt = modifiedAt;
    }
}
